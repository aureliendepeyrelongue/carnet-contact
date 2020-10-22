package com.lip6.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lip6.daos.DAOContact;
import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;
import com.lip6.util.Util;

@Service
public class ContactService {
	
	
	@Autowired
	private DAOContact daoContact;
	
	public List<Contact> getSearchedContacts(String search){
		
		String cleanedSearch = Util.cleanString(search);
		
		String [] arr = cleanedSearch.split(" ");
		
		for(int i = 0 ; i < arr.length; i ++) {
			arr[i] = arr[i].trim();
		}
		
		List<Contact> cList = daoContact.findAll();
		List<Contact> results = new ArrayList<Contact>();
		
		if(cList != null) {
			
			for(Contact c : cList) {
				boolean keep = false;
				String firstName = Util.cleanString(c.getFirstName());
				String lastName = Util.cleanString(c.getLastName());
				String email = Util.cleanString(c.getEmail());
				
				for(int i = 0 ; i < arr.length && !keep; i++) {
					String criteria = arr[i];
					if(firstName.contains(criteria) ||
							lastName.contains(criteria) ||
							email.contains(criteria)) {
						keep = true;
					}
					
				}
				if(keep) {
					results.add(c);
				}
		
			}
			
		}
		
		return results;
	}
	
	public void addContact(Map<String, String> params) {
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String email = params.get("email");
		String street = params.get("street");
		String city = params.get("city");
		String zip = params.get("zip");
		String country = params.get("country");
		int phone = Integer.parseInt(params.get("phone"));
		Address address = new Address(street, city, zip, country);
		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		
		for(int i=0; i<phone;i++) {
			String phonekind  = params.get("phonekind"+i);
			String phonenumber = params.get("phonenumber"+i);
			PhoneNumber pn = new PhoneNumber(phonekind,phonenumber);
			phones.add(pn);
		}
		
		Contact contact = new Contact(firstName, lastName, email, address, phones);
		
		for(PhoneNumber s: contact.getPhones()) {
			s.setContact(contact);
			contact.getPhones().add(s);
		}
		
		daoContact.addContact(contact);
	}

	public List<Contact> getAllContacts() {
		return daoContact.findAll();
	}
	
	public Contact getInfoContact(long Id) {
		return daoContact.infoContact(Id);
	}
	
	public void deleteContact(String Id) {
		daoContact.deleteContact(Long.parseLong(Id));
	}
	
	public void updateContact(Map<String, String> params) {
		String Id = params.get("Id");
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String email = params.get("email");
		String street = params.get("street");
		String city = params.get("city");
		String zip = params.get("zip");
		String country = params.get("country");
		int phone = Integer.parseInt(params.get("phone"));
		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		for(int i=1; i<=phone;i++) {
			String phoneKind  = params.get("phoneKind"+i);
			String phoneNumber = params.get("phoneNumber"+i);
			PhoneNumber pn = new PhoneNumber(phoneKind,phoneNumber);
			phones.add(pn);
		}
		daoContact.updateContact(Long.parseLong(Id), lastName, firstName, email, street, city, zip, country, phones);
	}
	
	public void addPhone(Map<String, String> params) {
		String Id = params.get("Id");
		int phone = Integer.parseInt(params.get("phone"));

		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		
		for(int i=0; i<phone;i++) {
			String phonekind  = params.get("phonekind"+i);
			String phonenumber = params.get("phonenumber"+i);
			PhoneNumber pn = new PhoneNumber(phonekind,phonenumber);
			phones.add(pn);
		}
		
		daoContact.addPhone(Long.parseLong(Id), phones);
	}
	
	public void deletePhone(Map<String, String> params) {
		String Id = params.get("Id");
		int phone = Integer.parseInt(params.get("phone"));
		Set<Long> ids = new HashSet<Long>();
		
		for(int i=1; i<=phone;i++) {
			String id_ = params.get("x"+i);
			if(id_ != null && !id_.isEmpty()) {
				ids.add(Long.parseLong(id_));
			}
		}
		
		daoContact.deletePhone(Long.parseLong(Id), ids);
	}
}

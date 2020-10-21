package com.lip6.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lip6.daos.DAOContact;
import com.lip6.entities.Contact;
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

	
}

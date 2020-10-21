package com.lip6.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.DAOContact;
import com.lip6.daos.DAOContactGroup;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;
import com.lip6.services.ContactService;

import java.util.Map;

@Controller
public class ContactController {
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/contact-create")
	public String getContactCreate() {
		return "contact-create";
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String addContact(@RequestParam Map<String, String> params) { 
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String email = params.get("email");
		String street = params.get("street");
		String city = params.get("city");
		String zip = params.get("zip");
		String country = params.get("country");
		int phone = Integer.parseInt(params.get("phone"));
		IDAOContact c = new DAOContact();
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
		
		c.addContact(contact);
		
	    return "redirect:contact-list";
	}
  	
	@RequestMapping("/contact-search")
	public String getContactSearch() {
		return "contact-search";
	}

	@RequestMapping(value="/contact-search", method = RequestMethod.POST)
	public String postContactSearch(ModelMap model,@RequestParam("search") String search) {
		
		List<Contact> results = contactService.getSearchedContacts(search);
		model.addAttribute("results", results);
		
		return "contact-search";
	}

	@RequestMapping("/contact-list")
	public String getListContact(Model model) {
		
		IDAOContact c = new DAOContact();
		List<Contact> x = c.allContact();
		model.addAttribute("contacts", c.allContact());
		
		for(Contact cs: x) {
			System.out.println(cs);
		}
		
		return "contact-list";
	}
	
	@RequestMapping(value="/findContact", method = RequestMethod.POST)
	public String postInfoContact(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		IDAOContact c = new DAOContact();
		Contact contact = c.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-info";
	}
	
	@RequestMapping(value="/getPhone", method = RequestMethod.POST)
	public String postgetPhone(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		IDAOContact c = new DAOContact();
		Contact contact = c.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-phone";
	}
	
	@RequestMapping(value="/getPhone2", method = RequestMethod.POST)
	public String postgetPhone2(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		IDAOContact c = new DAOContact();
		Contact contact = c.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-delete-phone";
	}
	
	@RequestMapping(value="/deleteContact", method = RequestMethod.POST)
	public String deleteContact(@RequestParam("Id") String Id) {
		
		IDAOContact c = new DAOContact();
		c.deleteContact(Long.parseLong(Id));
		return "redirect:contact-list";
	}
	
	@RequestMapping(value="/getContact", method = RequestMethod.POST)
	public String postgetContact(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		IDAOContact c = new DAOContact();
		Contact contact = c.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-update";
	}
	
	@RequestMapping(value="/updateContact", method = RequestMethod.POST)
	public String updateContact(@RequestParam Map<String, String> params) {
		String Id = params.get("Id");
		String firstName = params.get("firstName");
		String lastName = params.get("lastName");
		String email = params.get("email");
		String street = params.get("street");
		String city = params.get("city");
		String zip = params.get("zip");
		String country = params.get("country");
		int phone = Integer.parseInt(params.get("phone"));
		System.out.println(lastName);
		System.out.println(phone);
		System.out.println(firstName);
		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		for(int i=1; i<=phone;i++) {
			String phoneKind  = params.get("phoneKind"+i);
			String phoneNumber = params.get("phoneNumber"+i);
			System.out.println("phoneKind: " + phoneKind);
			System.out.println("phoneNumber: " + phoneNumber);
			PhoneNumber pn = new PhoneNumber(phoneKind,phoneNumber);
			phones.add(pn);
		}
		IDAOContact c = new DAOContact();
		System.out.println(phones);
		c.updateContact(Long.parseLong(Id), lastName, firstName, email, street, city, zip, country, phones);
		return "redirect:contact-list";
	}

	@RequestMapping(value="/addPhone", method = RequestMethod.POST)
	public String addPhone(@RequestParam Map<String, String> params) {
		String Id = params.get("Id");
		int phone = Integer.parseInt(params.get("phone"));
		System.out.println("phone: " + phone);
		
		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		
		for(int i=0; i<phone;i++) {
			String phonekind  = params.get("phonekind"+i);
			String phonenumber = params.get("phonenumber"+i);
			System.out.println("phonekind: " + phonekind);
			System.out.println("phonenumber: " + phonenumber);
			PhoneNumber pn = new PhoneNumber(phonekind,phonenumber);
			phones.add(pn);
		}
		
		IDAOContact c = new DAOContact();
		c.addPhone(Long.parseLong(Id), phones);
		
		return "redirect:contact-list";
		
	}
	
	@RequestMapping(value="/deletePhone", method = RequestMethod.POST)
	public String deletePhone(@RequestParam Map<String, String> params) {
		String Id = params.get("Id");
		int phone = Integer.parseInt(params.get("phone"));
		System.out.println("phone: " + phone);
		
		Set<Long> ids = new HashSet<Long>();
		
		for(int i=1; i<=phone;i++) {
			String id_ = params.get("x"+i);
			System.out.println("id_: " + id_);
			if(id_ != null && !id_.isEmpty()) {
				ids.add(Long.parseLong(id_));
			}
		}
		
		IDAOContact c = new DAOContact();
		c.deletePhone(Long.parseLong(Id), ids);
		
		return "redirect:contact-list";
		
	}
}

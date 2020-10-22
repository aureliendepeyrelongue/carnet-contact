package com.lip6.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lip6.daos.DAOContact;

import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;
import com.lip6.services.ContactService;

import java.util.Map;

@Controller
public class ContactController {
	
	@Autowired
	DAOContact daoContact;
	
	
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
		
		List<Contact> cList = daoContact.findAll();
		model.addAttribute("contacts", cList);
		
		return "contact-list";
	}
	
	@RequestMapping(value="/findContact", method = RequestMethod.POST)
	public String postInfoContact(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		Contact contact = daoContact.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-info";
	}
	
	@RequestMapping(value="/getPhone", method = RequestMethod.POST)
	public String postgetPhone(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {

		Contact contact = daoContact.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-phone";
	}
	
	@RequestMapping(value="/getPhone2", method = RequestMethod.POST)
	public String postgetPhone2(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {

		Contact contact = daoContact.infoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-delete-phone";
	}
	
	@RequestMapping(value="/deleteContact", method = RequestMethod.POST)
	public String deleteContact(@RequestParam("Id") String Id) {
		
		daoContact.deleteContact(Long.parseLong(Id));
		return "redirect:contact-list";
	}
	
	@RequestMapping(value="/getContact", method = RequestMethod.POST)
	public String postgetContact(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		Contact contact = daoContact.infoContact(Id);
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
		Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
		for(int i=1; i<=phone;i++) {
			String phoneKind  = params.get("phoneKind"+i);
			String phoneNumber = params.get("phoneNumber"+i);
			PhoneNumber pn = new PhoneNumber(phoneKind,phoneNumber);
			phones.add(pn);
		}
		daoContact.updateContact(Long.parseLong(Id), lastName, firstName, email, street, city, zip, country, phones);
		return "redirect:contact-list";
	}

	@RequestMapping(value="/addPhone", method = RequestMethod.POST)
	public String addPhone(@RequestParam Map<String, String> params) {
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
		
		return "redirect:contact-list";
		
	}
	
	@RequestMapping(value="/deletePhone", method = RequestMethod.POST)
	public String deletePhone(@RequestParam Map<String, String> params) {
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
		
		return "redirect:contact-list";
		
	}
}

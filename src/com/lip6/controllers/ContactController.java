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
import com.lip6.daos.DAOContactGroup;
import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;
import com.lip6.services.ContactGroupService;
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
		
		contactService.addContact(params);
		
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
		
		List<Contact> cList = contactService.getAllContacts();
		model.addAttribute("contacts", cList);
		
		return "contact-list";
	}
	
	@RequestMapping(value="/findContact", method = RequestMethod.POST)
	public String postInfoContact(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		Contact contact = contactService.getInfoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-info";
	}
	
	@RequestMapping(value="/getPhone", method = RequestMethod.POST)
	public String postgetPhone(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {

		Contact contact = contactService.getInfoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-phone";
	}
	
	@RequestMapping(value="/getPhone2", method = RequestMethod.POST)
	public String postgetPhone2(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {

		Contact contact = contactService.getInfoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-delete-phone";
	}
	
	@RequestMapping(value="/deleteContact", method = RequestMethod.POST)
	public String deleteContact(@RequestParam("Id") String Id) {
		
		contactService.deleteContact(Id);
		return "redirect:contact-list";
	}
	
	@RequestMapping(value="/getContact", method = RequestMethod.POST)
	public String postgetContact(@ModelAttribute("Id") long Id, BindingResult result,
            Model model) {
		
		Contact contact = contactService.getInfoContact(Id);
		model.addAttribute("contact", contact);
		return "contact-update";
	}
	
	@RequestMapping(value="/updateContact", method = RequestMethod.POST)
	public String updateContact(@RequestParam Map<String, String> params) {

		contactService.updateContact(params);
		return "redirect:contact-list";
	}

	@RequestMapping(value="/addPhone", method = RequestMethod.POST)
	public String addPhone(@RequestParam Map<String, String> params) {
		
		contactService.addPhone(params);
		return "redirect:contact-list";
		
	}
	
	@RequestMapping(value="/deletePhone", method = RequestMethod.POST)
	public String deletePhone(@RequestParam Map<String, String> params) {
		
		contactService.deletePhone(params);
		return "redirect:contact-list";
		
	}
}

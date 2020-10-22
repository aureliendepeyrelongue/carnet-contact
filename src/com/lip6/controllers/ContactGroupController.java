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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.DAOContact;
import com.lip6.daos.DAOContactGroup;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.PhoneNumber;
import com.lip6.services.ContactGroupService;
import com.lip6.services.ContactService;

import java.util.Map;
import java.util.Optional;

@Controller
public class ContactGroupController {
	
	@Autowired
	ContactGroupService contactGroupService;
	
	@Autowired
	ContactService contactService;
	
	
	@RequestMapping("/contact-group-create")
	public String getContactGroupCreate() {
		return "contact-group-create";
	}
	@RequestMapping(value="/contact-group-create", method=RequestMethod.POST)
	public String postContactGroupCreate(@RequestParam String groupName) {
		contactGroupService.addGroup(groupName);
		return "redirect:contact-group-list";
	}
	
	@RequestMapping("/contact-group-details")
	public String getContactGroupDetails(ModelMap model, @RequestParam long groupId) {
		ContactGroup cg = contactGroupService.getContactGroupById(groupId);
		model.addAttribute("contactGroup",cg);
		return "contact-group-details";
	}
	
	@RequestMapping("/contact-group-update")
	public String getContactGroupUpdate(ModelMap model, @RequestParam long groupId) {
		
		List<Contact> cList = contactService.getAllContacts();
		model.addAttribute("contactList",cList);
		
		
		ContactGroup cg = contactGroupService.getContactGroupById(groupId);
		model.addAttribute("contactGroup",cg);
		return "contact-group-update";
	}
	
	@RequestMapping(value="/contact-group-update",method=RequestMethod.POST)
	public String postContactGroupUpdate(ModelMap model,@RequestParam long groupId, @RequestParam String groupName, @RequestParam Optional<long[]> contacts) {
		
		long[] newContacts= {};
		
		if(contacts.isPresent()) {	
			newContacts = contacts.get();
		}

		contactGroupService.updateGroupByContacts(groupId,groupName, newContacts);
		ContactGroup cg = contactGroupService.getContactGroupById(groupId);
		model.addAttribute("contactGroup",cg);
		return "contact-group-details";
	}
	
	
	@RequestMapping("/contact-group-delete")
	public String postContactGroupDelete(ModelMap model,@RequestParam long groupId) {
		contactGroupService.removeContactGroup(groupId);
		return "redirect:contact-group-list";
	}
	
	@RequestMapping("/contact-group-list")
	public String getContactGroupList(ModelMap model) {
		List<ContactGroup> cgList = contactGroupService.getAllGroup();
		model.addAttribute("groupList", cgList);
		return "contact-group-list";
	}
	
	@RequestMapping("/contact-group-search")
	public String getContactGroupSearch() {
		return "contact-group-search";
	}
	
	@RequestMapping(value="/contact-group-search", method = RequestMethod.POST)
	public String postContactSearch(ModelMap model,@RequestParam("search") String search) {
		
		List<ContactGroup> results = contactGroupService.getSearchedContactGroups(search);
		model.addAttribute("results", results);
		
		return "contact-group-search";
	}
}

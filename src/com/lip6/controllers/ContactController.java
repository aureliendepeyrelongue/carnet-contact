package com.lip6.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.DAOContact;
import com.lip6.daos.DAOContactGroup;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;

import java.util.Map;

@Controller
public class ContactController {
	
	
	@RequestMapping("/contact-create")
	public String getContactCreate() {
		return "contact-create";
	}
	
	@RequestMapping("/contact-search")
	public String getContactSearch() {
		return "contact-search";
	}
	
	@RequestMapping(value="/contact-search-post", method = RequestMethod.POST)
	public String postContactSearch(@RequestParam("search") String search) {
		
		System.out.println(search);
		
		return "contact-search";
	}

}

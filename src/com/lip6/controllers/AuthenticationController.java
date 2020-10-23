package com.lip6.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lip6.daos.DAOContact;
import com.lip6.entities.Contact;
import com.lip6.services.ContactService;

@Controller
public class AuthenticationController {
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}
	
	@RequestMapping(value="/login", method= RequestMethod.POST)
	public String postLogin(HttpServletRequest request, ModelMap model, @RequestParam String email, @RequestParam String lastName) {
		HttpSession session = request.getSession();

		Contact c = contactService.findContactByLastNameAndEmail(lastName, email);
		
		if(c != null) {
			session.setAttribute("authenticated", "true");
			session.setAttribute("userId", c.getId());
			session.setAttribute("firstName", c.getFirstName());
			model.addAttribute("loginAncestor", "true");
		
			return "index";
		}
		else {
			model.addAttribute("badAuthentication", "true");
			
			return "login";
		}
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.POST)
	public String getPostLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("authenticated");
		session.removeAttribute("userId");
		session.removeAttribute("firstName");
		return "index";
	}
	

}

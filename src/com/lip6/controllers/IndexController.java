package com.lip6.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.DAOContact;
import com.lip6.daos.DAOContactGroup;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;

import java.util.Map;

@Controller
public class IndexController  {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	DAOContact cdao ;
 
	@RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value="/test-bean", method= RequestMethod.GET)
    public String testBean() {
   		String[] allBeanNames = context.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName + "******************");
        }
        
        DAOContact dao = new DAOContact();
        Contact c =(Contact) context.getBean("contact1");
        Contact c2 =(Contact) context.getBean("contact2");

        dao.addContact(c2);
        dao.addContact(c);
        
        return "index";
    }
    
    @RequestMapping("/add-data")
    public String addData() {
    
    	Contact contact1 = (Contact)context.getBean("contact1");
    	Contact contact2 = (Contact)context.getBean("contact2");
    	cdao.addContact(contact1);
    	cdao.addContact(contact2);
 
      return "index";
    }


}

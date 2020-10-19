package com.lip6.controllers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;



import org.springframework.context.ApplicationContext;
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

       
 
	@RequestMapping("/")
    public String index() {
      
        return "index";
    }

    @RequestMapping(value="/test", method= RequestMethod.GET)
    public String test() {
   
        return "test";
    }


}

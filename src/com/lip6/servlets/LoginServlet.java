package com.lip6.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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


@Controller
public class LoginServlet  {
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Autowired
	private ApplicationContext context;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    @RequestMapping("/blabla")
	protected String index(){

    	return "hello";
	}
    @RequestMapping("get")
    @ResponseBody
	protected String get() {
		// TODO Auto-generated method stub
           return "Ecriture database";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value="post",method=RequestMethod.POST)
	protected String post() throws ServletException, IOException {
		// TODO Auto-generated method stub
   
		String[] allBeanNames = context.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName + "******************");
        }
		
		IDAOContact idao = (IDAOContact) context.getBean("cdao");		
		
		Contact c = (Contact) context.getBean("contact1");
		//Contact c2 = (Contact) context.getBean("contact2");
		
		idao.addContact(c);
	
	    return get();
	}

}

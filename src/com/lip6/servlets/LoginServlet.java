package com.lip6.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.DAOContact;
import com.lip6.daos.DAOContactGroup;
import com.lip6.daos.IDAOContact;
import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		
		String[] allBeanNames = context.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName + "******************");
        }
		
		IDAOContact idao = (IDAOContact) context.getBean("cdao");		
		
		Contact c = (Contact) context.getBean("contact1");
		//Contact c2 = (Contact) context.getBean("contact2");
		
		idao.addContact(c);
		//idao.addContact(c2);
		
		
		
		
	/*	DAOContact c = new DAOContact();
		//DAOGroup g = new DAOGroup();
		//c.addContact("Thibault", "Anani", "thuny.ta@gmail.com");
		c.addContact("Thibault", "Anani", "thuny.ta@gmail.com","Square des corolles", "Courbevoie", "11", "France");
		//g.addGroup("M2 Miage");
		DAOContactGroup g = new DAOContactGroup();
		g.addContactToContactGroup(1, 2);
		g.createGroup("Doctorants MIAGE");
		//g.removeContactToContactGroup(1, 1);
		g.removeContactGroup(1);
		*/
		
		doGet(request, response);
	}

}

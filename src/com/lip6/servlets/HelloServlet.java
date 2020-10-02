package com.lip6.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;



@Controller
@RequestMapping(value="/hello")
public class HelloServlet {
	  /**
	 * 
	 */



	  
	
	@RequestMapping("/test")
	@ResponseBody
	public String index() {
		return "Coucou mon ami";
	}
	
}

package com.lip6.util;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.lip6.entities.Contact;

@Component
public class JSPUtil {

	
	public static boolean contactIsInSet(Contact c, Set<Contact> contacts) {
		boolean test = false;
		if(contacts == null) {
			return test;
		}
		for(Contact cf : contacts) {
			if(cf.getId() == c.getId()) {
				test=true;
				break;
			}
		}
		
		return test;
	}
	
}

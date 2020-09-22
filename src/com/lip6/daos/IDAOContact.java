package com.lip6.daos;

import com.lip6.entities.Contact;

public interface IDAOContact {

	
	public boolean addContact(String lastname, String firstname, String email, String street, String city, String zip, String country);

	public boolean addContact(Contact contact);

	
}

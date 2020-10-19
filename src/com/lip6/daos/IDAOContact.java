package com.lip6.daos;

import org.springframework.stereotype.Repository;

import com.lip6.entities.Contact;

@Repository

public interface IDAOContact {

	
	public boolean addContact(String lastname, String firstname, String email, String street, String city, String zip, String country);

	public boolean addContact(Contact contact);

	
}

package com.lip6.daos;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


import com.lip6.entities.Contact;
import com.lip6.entities.PhoneNumber;

@Repository

public interface IDAOContact {

	public boolean addContact(String lastname, String firstname, String email, String street, String city, String zip, String country);

	public boolean addContact(Contact contact);

	public List<Contact> allContact();
	
	public Contact infoContact(long Id);
	
	public boolean deleteContact(long Id);
	
	public boolean updateContact(long Id, String lastname, String firstname, String email, String street, String city, String zip, String country, Set<PhoneNumber> phones);
	
	public boolean addPhone(long Id, Set<PhoneNumber> phones);
	
	public boolean deletePhone(long Id, Set<Long> ids);
}

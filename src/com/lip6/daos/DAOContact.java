package com.lip6.daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.PhoneNumber;
import com.lip6.util.JpaUtil;

@Repository
public class DAOContact implements IDAOContact{
	
	public boolean addContact(Contact contact) {
	
	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();
		EntityTransaction tx =  em.getTransaction();
		tx.begin();

		em.persist(contact);
		
		tx.commit();
		em.close();
	
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
		
	}
	
	
	public Contact infoContact(long Id) {

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		 
		Contact contact = em.find(Contact.class, Id);
		 
		tx.commit();
		em.close();
		
		return contact;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public boolean deleteContact(long Id) {
    
	    boolean success=false;
		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		 
		Contact contact = em.find(Contact.class, Id);
		contact.setAddress(null);
		em.remove(contact);

		tx.commit();
		em.close();
		
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
	}
	
	public boolean updateContact(long Id, String lastname, String firstname, String email, String street, String city, String zip, String country, Set<PhoneNumber> phones) {

	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		 
		Contact contact = em.find(Contact.class, Id);
		
		
		contact.setLastName(lastname);
		contact.setFirstName(firstname);
		contact.setEmail(email);
		
		contact.getAddress().setStreet(street);
		contact.getAddress().setCity(city);
		contact.getAddress().setZip(zip);
		contact.getAddress().setCountry(country);
		
		Iterator<PhoneNumber> it1 = contact.getPhones().iterator();
		Iterator<PhoneNumber> it2 = contact.getPhones().iterator();
		Iterator<PhoneNumber> it3 = phones.iterator();
		Iterator<PhoneNumber> it4 = phones.iterator();
		
		while(it1.hasNext() && it2.hasNext() && it3.hasNext() && it4.hasNext()) {
			it1.next().setPhoneKind(it3.next().getPhoneKind());
			it2.next().setPhoneNumber(it4.next().getPhoneNumber());
		}

		tx.commit();
		em.close();
		
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
	}
	
	public boolean addPhone(long Id, Set<PhoneNumber> phones) {
	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		Contact contact = em.find(Contact.class, Id);
		
		for(PhoneNumber p: phones) {
			contact.addPhoneNumber(p);
		}
		
		for(PhoneNumber s: contact.getPhones()) {
			s.setContact(contact);
			contact.getPhones().add(s);
		}

		tx.commit();
		em.close();
		
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
	}
	

	@SuppressWarnings("unchecked")
	public List<Contact> findAll(){
		List<Contact> cList = null;
		try {
		EntityManager em=JpaUtil.getEmf().createEntityManager(); 
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		cList = (List<Contact>)em.createQuery("SELECT c FROM Contact c").getResultList();
		tx.commit();
		em.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return cList;
	}
	
	@SuppressWarnings("unchecked")
	public Contact findContactByLastNameAndEmail(String lastName, String email){
	   Contact contact = null;
		
		try {
		    EntityManager em=JpaUtil.getEmf().createEntityManager();

			EntityTransaction tx =  em.getTransaction();
			tx.begin();
		
			List<Contact>cList = (List<Contact>)em.createQuery("SELECT c FROM Contact c WHERE c.lastName LIKE :lastName AND c.email LIKE :email")
					.setParameter("lastName", lastName)
					.setParameter("email", email)
					.getResultList();

			contact = cList.size() > 0 ? cList.get(0) : null;
			
			tx.commit();
			em.close();
		  
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return contact;
	}
	

	public boolean deletePhone(long Id, Set<Long> ids) {
	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		Contact contact = em.find(Contact.class, Id);
		
		for(long l: ids) {	
			PhoneNumber number = em.find(PhoneNumber.class, l);		
			contact.removePhoneNumber(number);	
		}

		tx.commit();

		em.close();
		
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
	}

	
}

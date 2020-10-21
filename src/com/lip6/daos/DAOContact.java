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

	
	@Override
	public boolean addContact(String firstname, String lastname, String email, String street, String city, String zip, String country) {
		
		//Avant l'utilisation de classe JpaUtil	
		//EntityManagerFactory emf=Persistence.createEntityManagerFactory("projetJPA");
		
		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		
	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		// 2 : Ouverture transaction 
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		// 3 : Instanciation Objet(s) m�tier (s)
		Address address = new Address(street, city, zip, country);
		Contact contact = new Contact(lastname,firstname, email, address);
		PhoneNumber phone1 = new PhoneNumber("Fixe", "0769320101");
		PhoneNumber phone2 = new PhoneNumber("Portable", "0101010101");
		contact.addPhoneNumber(phone1);
		contact.addPhoneNumber(phone2);
		phone1.setContact(contact);
		phone2.setContact(contact);
		
		Address address2 = new Address(street, city, zip, country);
		Contact contact2 = new Contact(lastname,firstname, email, address);
		PhoneNumber phone12 = new PhoneNumber("Fixe", "0769320101");
		PhoneNumber phone22 = new PhoneNumber("Portable", "0101010101");
		contact2.addPhoneNumber(phone12);
		contact2.addPhoneNumber(phone22);
		phone12.setContact(contact2);
		phone22.setContact(contact2);
		
		ContactGroup group = new ContactGroup("M2 MIAGE");
		group.getContacts().add(contact);
		contact.getContactGroups().add(group);
		
		// 4 : Persistance Objet/Relationnel : cr�ation d'un enregistrement en base
		 
		
		em.persist(contact);
		em.persist(contact2);
		
		//ici l'objet est dans un �tat manag� par l'EM, pas besoin d'un nouveau persist
		contact.setLastName("Blanquito");
		
		// 5 : Fermeture transaction 
		tx.commit();
		
		//ici l'objet est dans un �tat d�tach� de l'EM, la modif ne sera pas commit�e
		contact.setLastName("Blanchard");
		 
		// 6 : Fermeture de l'EntityManager et de unit� de travail JPA 
		String requete = "SELECT c.firstName, c.lastName, c.address.city FROM Contact c ORDER BY c.firstName DESC, c.lastName ";
		Query query = em.createQuery(requete);
		List <Object[]> results = query.getResultList();
		for (Object[] result : results) {
		System.out.println("nom: " + result[0] + ", prenom: " + result[1] + ", ville: " + result[2] );
		}
		em.close();
		
		
		
		// 7: Attention important, cette action ne doit s'executer qu'une seule fois et non pas à chaque instantiation du ContactDAO
		//Donc, pense bien à ce qu'elle soit la dernière action de votre application
		//JpaUtil.close();	
		
		// Tout faire dans addContact (num�ro de t�l�phone/ adresse pour l'instant)
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
		
	}
	
	public boolean addContact(Contact contact) {
		
		//Avant l'utilisation de classe JpaUtil	
		//EntityManagerFactory emf=Persistence.createEntityManagerFactory("projetJPA");
		
		//1: obtenir une connexion et un EntityManager, en passant par la classe JpaUtil
		
	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		// 2 : Ouverture transaction 
		EntityTransaction tx =  em.getTransaction();
		tx.begin();
		
		// 3 : Instanciation Objet(s) m�tier (s)
	
		
		
		// 4 : Persistance Objet/Relationnel : cr�ation d'un enregistrement en base
		 
		
		em.persist(contact);
		
		//ici l'objet est dans un �tat manag� par l'EM, pas besoin d'un nouveau persist
	
		
		// 5 : Fermeture transaction 
		tx.commit();
		
		
		 
		// 6 : Fermeture de l'EntityManager et de unit� de travail JPA 
		em.close();
		
		// 7: Attention important, cette action ne doit s'executer qu'une seule fois et non pas à chaque instantiation du ContactDAO
		//Donc, pense bien à ce qu'elle soit la dernière action de votre application
		//JpaUtil.close();	
		
		// Tout faire dans addContact (num�ro de t�l�phone/ adresse pour l'instant)
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
		
	}
	
	public List<Contact> allContact() {

        EntityManager em=JpaUtil.getEmf().createEntityManager();
        EntityTransaction tx =  em.getTransaction();
        tx.begin();

        final String requete = "SELECT * FROM contacts";
        List<Contact> allcontact=em.createNativeQuery(requete,Contact.class).getResultList();
        return allcontact;
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
			cList = (List<Contact>)em.createQuery("SELECT c FROM Contact c").getResultList();
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
		    EntityManager em= JpaUtil.getEmf().createEntityManager();
		    
			List<Contact>cList = (List<Contact>)em.createQuery("SELECT c FROM Contact c WHERE c.lastName LIKE :lastName AND c.email LIKE :email")
					.setParameter("lastName", lastName)
					.setParameter("email", email)
					.getResultList();

			contact = cList.size() > 0 ? cList.get(0) : null;
		  
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
			System.out.println(l);
			PhoneNumber number = em.find(PhoneNumber.class, l);
			System.out.println(number.getPhoneKind());
			System.out.println(number.getPhoneNumber());
			System.out.println(contact.getPhones().size());
			contact.removePhoneNumber(number);
			System.out.println(contact.getPhones().size());
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

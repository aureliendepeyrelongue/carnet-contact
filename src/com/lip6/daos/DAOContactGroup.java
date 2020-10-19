package com.lip6.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.PhoneNumber;
import com.lip6.util.JpaUtil;

@Repository
public class DAOContactGroup implements IDAOContactGroup {

	@Override
	public boolean createGroup(String groupName) {
		
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
		ContactGroup group = new ContactGroup(groupName);
		
		
		// 4 : Persistance Objet/Relationnel : cr�ation d'un enregistrement en base
		 
		
		em.persist(group);
		
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

	public boolean addContactToContactGroup(long groupId, long contactId) {
		boolean success = false;
		
		try {
		    EntityManager em=JpaUtil.getEmf().createEntityManager();
			// 2 : Ouverture transaction 
			EntityTransaction tx =  em.getTransaction();
			tx.begin();
			Contact c = em.find(Contact.class,contactId);
			ContactGroup g = em.find(ContactGroup.class,groupId);
			g.getContacts().add(c);
			c.getContactGroups().add(g);
			
			em.persist(g);
			tx.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		
		return success;
		
	}
	
	public boolean removeContactToContactGroup(long groupId, long contactId) {
		boolean success = false;
		
		try {
		    EntityManager em=JpaUtil.getEmf().createEntityManager();
			// 2 : Ouverture transaction 
			EntityTransaction tx =  em.getTransaction();
			tx.begin();
			Contact c = em.find(Contact.class,contactId);
			ContactGroup g = em.find(ContactGroup.class,groupId);
			g.removeContact(c);
			
			em.persist(g);
			tx.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		
		return success;
		
	}
	
	public boolean removeContactGroup(long groupId) {
	boolean success = false;
		
		try {
		    EntityManager em=JpaUtil.getEmf().createEntityManager();
			// 2 : Ouverture transaction 
			EntityTransaction tx =  em.getTransaction();
			tx.begin();
			ContactGroup g = em.find(ContactGroup.class,groupId);
		    em.remove(g);			
			tx.commit();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			
		}
		
		
		return success;
	}
}

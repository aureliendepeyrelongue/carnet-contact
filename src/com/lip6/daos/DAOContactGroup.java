package com.lip6.daos;

import java.util.*;

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
		

	    boolean success=false;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		EntityTransaction tx =  em.getTransaction();
		tx.begin();

		ContactGroup group = new ContactGroup(groupName);
		
		
		em.persist(group);
	
		tx.commit();
		
		em.close();
		
	
		success=true;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return success;
		
	}
	
	@Override
	public ContactGroup getContactGroupById(long id) {
		
		ContactGroup group = null;

		try {
	    EntityManager em=JpaUtil.getEmf().createEntityManager();

		 group = em.find(ContactGroup.class,id);

		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return group;
		
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
	@SuppressWarnings("unchecked")
	public List<ContactGroup> findAll(){
		List<ContactGroup> cList = null;
		
		try {
		    EntityManager em=JpaUtil.getEmf().createEntityManager();
		    
			cList = (List<ContactGroup>)em.createQuery("SELECT cg FROM ContactGroup cg").getResultList();
		  
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return cList;
	}
	
	
	public boolean updateGroupByContacts(long groupId,String groupName, long[] contactIds) {
    boolean success = false;
		
		try {
		    EntityManager em=JpaUtil.getEmf().createEntityManager();
			// 2 : Ouverture transaction 
		  
			EntityTransaction tx =  em.getTransaction();
			tx.begin();
			  Set<Contact> newContacts = new HashSet<Contact>();
		
			ContactGroup g = em.find(ContactGroup.class,groupId);
			g.setGroupName(groupName);
		    Set<Contact> oldContacts = g.getContacts();
		    for(Contact c: oldContacts) {
		    	c.getContactGroups().remove(g);
		    }
			for(long cId : contactIds) {
			  newContacts.add(em.find(Contact.class,cId));
			}
			
			for(Contact c : newContacts) {
				c.getContactGroups().add(g);
			}
			g.setContacts(newContacts);
		    
			em.persist(g);
		    			
			tx.commit();
			em.close();
			success = true;
		}
		catch (Exception e) {
			e.printStackTrace();
	
		}
		
		
		return success;
	}
		
	}


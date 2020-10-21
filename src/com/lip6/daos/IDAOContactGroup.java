package com.lip6.daos;

import org.springframework.stereotype.Repository;

import com.lip6.entities.ContactGroup;

@Repository
public interface IDAOContactGroup {

	public boolean createGroup(String groupname);
	public ContactGroup getContactGroupById(long id);
	
}

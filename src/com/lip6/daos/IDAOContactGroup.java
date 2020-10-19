package com.lip6.daos;

import org.springframework.stereotype.Repository;

@Repository
public interface IDAOContactGroup {

	public boolean createGroup(String groupname);
	
}

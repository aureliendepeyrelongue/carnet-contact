package com.lip6.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lip6.daos.DAOContactGroup;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.util.Util;

@Service
public class ContactGroupService {
	
	
	@Autowired
	private DAOContactGroup daoContactGroup;
	
	public List<ContactGroup> getSearchedContactGroups(String search){
		
		String cleanedSearch = Util.cleanString(search);
		
		String [] arr = cleanedSearch.split(" ");
		
		for(int i = 0 ; i < arr.length; i ++) {
			arr[i] = arr[i].trim();
		}
		
		List<ContactGroup> cList = daoContactGroup.findAll();
		List<ContactGroup> results = new ArrayList<ContactGroup>();
		
		if(cList != null) {	
			for(ContactGroup cg : cList) {
				boolean keep = false;
				String name = Util.cleanString(cg.getGroupName());
				for(int i = 0 ; i < arr.length && !keep; i++) {
					String criteria = arr[i];
					if(name.contains(criteria)) {
						keep = true;
					}
				}
				if(keep) {
					results.add(cg);
				}
		
			}
			
		}
		
		return results;
	}
	


}

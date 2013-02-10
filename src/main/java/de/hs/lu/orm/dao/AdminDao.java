package de.hs.lu.orm.dao;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Admin;
import de.hs.lu.orm.AbstractDao;

@Component("adminDao")
public class AdminDao extends AbstractDao<Admin>{
	
	public AdminDao(){
    	super(Admin.class);
    }

}

package de.hs.lu.orm.dao;

import org.springframework.stereotype.Component;

import de.hs.lu.model.ZusatzService;
import de.hs.lu.orm.AbstractDao;

@Component("zusatzServiceDao")
public class ZusatzServiceDao extends AbstractDao<ZusatzService>{
	
	public ZusatzServiceDao(){
    	super(ZusatzService.class);
    }	

}

package de.hs.lu.orm.dao;

import org.springframework.stereotype.Component;

import de.hs.lu.model.ReservierungsService;
import de.hs.lu.orm.AbstractDao;

@Component("reservierungServiceDao")
public class ReservierungsServiceDao extends AbstractDao<ReservierungsService>{
	
	public ReservierungsServiceDao(){
    	super(ReservierungsService.class);
    }

}

package de.hs.lu.orm.dao;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Bewertung;
import de.hs.lu.orm.AbstractDao;

@Component("bewertungDao")
public class BewertungDao extends AbstractDao<Bewertung>{
	
	public BewertungDao(){
    	super(Bewertung.class);
    }
}

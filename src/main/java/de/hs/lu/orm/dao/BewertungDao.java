package de.hs.lu.orm.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Bewertung;
import de.hs.lu.orm.AbstractDao;

@Component("bewertungDao")
public class BewertungDao extends AbstractDao<Bewertung>{
	
	public BewertungDao(){
    	super(Bewertung.class);
    }
	
    public List<Bewertung> findAll(){
        return entityManager.createQuery("SELECT b FROM Bewertung b", Bewertung.class).getResultList();
    }
}

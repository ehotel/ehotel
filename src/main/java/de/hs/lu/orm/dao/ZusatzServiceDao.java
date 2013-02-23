package de.hs.lu.orm.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import de.hs.lu.model.ZusatzService;
import de.hs.lu.orm.AbstractDao;

@Component("zusatzServiceDao")
public class ZusatzServiceDao extends AbstractDao<ZusatzService>{
	
	public ZusatzServiceDao(){
    	super(ZusatzService.class);
	}
	
    public List<ZusatzService> findAll(){
        return entityManager.createQuery("SELECT o FROM ZusatzService o", ZusatzService.class).getResultList();
    }

}

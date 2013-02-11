package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Gast;
import de.hs.lu.orm.AbstractDao;

@Component("gastDao")
public class GastDao extends AbstractDao<Gast>{
	
    @PersistenceContext
    protected EntityManager entityManager;
	
	public GastDao(){
    	super(Gast.class);
	}
	
	@SuppressWarnings("unchecked")
	public Gast findGastByBenutzername(String userName)
	{
		Query query = entityManager.createQuery("select g FROM Gast g where g.benutzername= :benutzername");
		query.setParameter("benutzername", userName);
		List<Gast> gast = query.getResultList();
		if(gast != null && gast.size() == 1)
		{
			return gast.get(0);
		}		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Gast findGastByEMAil(String email)
	{
		Query query = entityManager.createQuery("select g FROM Gast g where g.email= :email");
		query.setParameter("email", email);
		List<Gast> gast = query.getResultList();
		if(gast != null && gast.size() == 1)
		{
			return gast.get(0);
		}		
		return null;		
	}
	
	@SuppressWarnings("unchecked")
	public Gast findGastByAktivierungsHash(String hash)
	{
		Query query = entityManager.createQuery("select g FROM Gast g where g.aktivierungsHash= :aktivierungs_hash");
		query.setParameter("aktivierungs_hash", hash);
		List<Gast> gast = query.getResultList();
		if(gast != null && gast.size() == 1)
		{
			return gast.get(0);
		}		
		return null;		
	}

}

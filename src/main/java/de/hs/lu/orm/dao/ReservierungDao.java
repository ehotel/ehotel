package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Gast;
import de.hs.lu.model.Reservierung;
import de.hs.lu.orm.AbstractDao;

@Component("reservierungDao")
public class ReservierungDao extends AbstractDao<Reservierung>{
	
	public ReservierungDao(){
    	super(Reservierung.class);
    }
	
	@SuppressWarnings("unchecked")
    public List<Reservierung> findReservierungenByGast(Gast gast)
    {   
		Query query = entityManager.createQuery("select r FROM Reservierung r where r.gast= :gast");
		query.setParameter("gast", gast);
		List<Reservierung> reservierungen = query.getResultList();		
		return reservierungen;
    }

}

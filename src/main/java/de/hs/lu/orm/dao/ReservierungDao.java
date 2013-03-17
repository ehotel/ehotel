package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.model.Bewertung;
import de.hs.lu.model.Gast;
import de.hs.lu.model.Reservierung;
import de.hs.lu.model.ReservierungsService;
import de.hs.lu.model.Zimmer;
import de.hs.lu.orm.AbstractDao;

@Component("reservierungDao")
public class ReservierungDao extends AbstractDao<Reservierung>{
	
	@Autowired
	private ReservierungsServiceDao rsDao;
	
	@Autowired
	private BewertungDao bewertungDao;
	
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
	
	@SuppressWarnings("unchecked")
    public List<Reservierung> findReservierungenByZimmer(Zimmer zimmer)
    {   
		Query query = entityManager.createQuery("select r FROM Reservierung r where r.zimmer= :zimmer");
		query.setParameter("zimmer", zimmer);
		List<Reservierung> reservierungen = query.getResultList();
		return reservierungen;
    }
	
    public List<Reservierung> findAll(){
        return entityManager.createQuery("SELECT r FROM Reservierung r", Reservierung.class).getResultList();
    }
    
    @Transactional
    public void remove(Reservierung res)
    {
        for (; res.getReservierungsServices().size() > 0; ) {
        	ReservierungsService rs = res.getReservierungsServices().iterator().next();
            rsDao.remove(rsDao.getReference(rs.getId()));
            res.getReservierungsServices().remove(rs);
        }        
        
        if (res.getBewertungen() != null) {
        	Bewertung bewertung  = res.getBewertungen();
        	bewertung.setReservierung(null);        	
        	bewertungDao.merge(bewertung);        	
        }
        super.remove(getReference(res.getId()));
        
    }

}

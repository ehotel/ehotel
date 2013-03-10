package de.hs.lu.orm.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.model.Gast;
import de.hs.lu.model.Reservierung;
import de.hs.lu.model.ReservierungsService;
import de.hs.lu.orm.AbstractDao;

@Component("reservierungDao")
public class ReservierungDao extends AbstractDao<Reservierung>{
	
	@Autowired
	private ReservierungsServiceDao rsDao;
	
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
	
    public List<Reservierung> findAll(){
        return entityManager.createQuery("SELECT r FROM Reservierung r", Reservierung.class).getResultList();
    }
    
    @Transactional
    public void remove(Reservierung res)
    {
        for (; res.getReservierungsServices().size() > 0; ) {
        	Iterator<ReservierungsService> resIt = res.getReservierungsServices().iterator();
        	ReservierungsService rs = resIt.next();
            rsDao.remove(rsDao.getReference(rs.getId()));
            resIt.remove();
            res.getReservierungsServices().remove(rs);
        }
        super.remove(getReference(res.getId()));
    }

}

package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Gast;
import de.hs.lu.model.ReservierungsService;
import de.hs.lu.orm.AbstractDao;

@Component("reservierungServiceDao")
public class ReservierungsServiceDao extends AbstractDao<ReservierungsService>{
	
	public ReservierungsServiceDao(){
    	super(ReservierungsService.class);
    }
	
    public List<ReservierungsService> findAll(){
        return entityManager.createQuery("SELECT o FROM ReservierungsService o", ReservierungsService.class).getResultList();
    }
    
    @SuppressWarnings("unchecked")
	public List<Object[]> findReservierungsServiceByStartAndEnd(long start, long ende){
		
		String where ="o.startdatum<= :start and o.enddatum>= :start or "
					+ "o.startdatum<= :ende  and o.enddatum>= :ende or "
					+ "o.startdatum>= :start and o.enddatum<= :ende";
		
		Query query = entityManager.createQuery("select zusatzService, count(*) as anzahl FROM ReservierungsService o where " + where + " group by zusatz_service_id");
		query.setParameter("start", start);
		query.setParameter("ende", ende);
		List<Object[]> reservierungService = query.getResultList();
		
		return reservierungService;
	}
	
	@SuppressWarnings("unchecked")
    public List<Gast> findReservierungenByGast(Gast gast)
    {   
		Query query = entityManager.createQuery("select r FROM Reservierung r where r.gast= :gast");
		query.setParameter("gast", gast);
		List<Gast> gaeste = query.getResultList();		
		return gaeste;
    }
    

}

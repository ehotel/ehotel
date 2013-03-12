package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.model.Reservierung;
import de.hs.lu.model.Zimmer;
import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.AbstractDao;

@Component("zimmerDao")
public class ZimmerDao extends AbstractDao<Zimmer> {

	@Autowired
	private ReservierungDao resDao;
	
	private final Log logger = LogFactory.getLog(ZimmerDao.class);
	
	public ZimmerDao(){
    	super(Zimmer.class);
    }	
	
	@SuppressWarnings("unchecked")
	public Zimmer findZimmerByZimmerNr(int zimmerNr)
	{
		Query query = entityManager.createQuery("select z FROM Zimmer z where z.zimmerNr= :zimmernr");
		query.setParameter("zimmernr", zimmerNr);
		List<Zimmer> zimmer = query.getResultList();
		if(zimmer != null)
		{
			if(zimmer.size() == 1)
			{
				return zimmer.get(0);
			}
			logger.info("Mehr als ein Zimmer mit selben ZimmerNr gefunden");			
		}		
		return null;
	}
	
    public List<Zimmer> findAll(){
        return entityManager.createQuery("SELECT o FROM Zimmer o", Zimmer.class).getResultList();
    }
    
	@SuppressWarnings("unchecked")
    public List<Zimmer> findZimmerByZimmerkategorie(Zimmerkategorie zimmerkategorie)
    {   
		Query query = entityManager.createQuery("select z FROM Zimmer z where z.zimmerkategorie= :zk");
		query.setParameter("zk", zimmerkategorie);
		List<Zimmer> zimmer = query.getResultList();		
		return zimmer;    	
    }
	
    @Transactional
    public void remove(Zimmer z)
    {
        for (; z.getReservierungen().size() > 0; ) {
        	Reservierung res = z.getReservierungen().iterator().next();
            resDao.remove(resDao.getReference(res.getId()));
            z.getReservierungen().remove(res);
        }
        super.remove(getReference(z.getId()));
    }
	
}

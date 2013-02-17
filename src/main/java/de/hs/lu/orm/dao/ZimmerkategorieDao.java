package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.AbstractDao;

@Component("zimmerkategorieDao")
public class ZimmerkategorieDao extends AbstractDao<Zimmerkategorie>{
	
	private final Log logger = LogFactory.getLog(ZimmerkategorieDao.class);
	
	public ZimmerkategorieDao(){
    	super(Zimmerkategorie.class);
    }	
	
	@SuppressWarnings("unchecked")
	public Zimmerkategorie findZimmerkategorieByZimmertyp(String zimmertyp)
	{
		Query query = entityManager.createQuery("select z FROM Zimmerkategorie z where z.zimmertyp= :zimmertyp");
		query.setParameter("zimmertyp", zimmertyp);
		List<Zimmerkategorie> zimmerkategorie = query.getResultList();
		if(zimmerkategorie != null)
		{
			if(zimmerkategorie.size() == 1)
			{
				return zimmerkategorie.get(0);
			}
			logger.info("Mehr als eine Zimmerkategorie gefunden die den selben Typ hat");			
		}		
		return null;
	}
	
	
    public List<Zimmerkategorie> findAll(){
        return entityManager.createQuery("SELECT o FROM Zimmerkategorie o", Zimmerkategorie.class).getResultList();
    }

}

package de.hs.lu.orm.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;

import de.hs.lu.model.Zimmer;
import de.hs.lu.orm.AbstractDao;

@Component("zimmerDao")
public class ZimmerDao extends AbstractDao<Zimmer> {
	
	@SuppressWarnings("unchecked")
	public Zimmer findZimmerByZimmerNr(int zimmerNr)
	{
		Query query = entityManager.createQuery("select z FROM Zimmer z where z.zimmerNr= :zimmernr");
		query.setParameter("zimmernr", zimmerNr);
		List<Zimmer> zimmer = query.getResultList();
		if(zimmer != null && zimmer.size() == 1)
		{
			return zimmer.get(0);
		}
		
		return null;
	}
	
	

}

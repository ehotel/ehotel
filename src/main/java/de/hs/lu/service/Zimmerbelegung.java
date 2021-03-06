package de.hs.lu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.model.Reservierung;
import de.hs.lu.model.Status;
import de.hs.lu.model.Zimmer;
import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

@Component
public class Zimmerbelegung {
	
	@Autowired
	private ZimmerkategorieDao zimmerkategorieDao;
	
	@Transactional
	public Zimmer freieZimmerSuche(String zimmertyp, long start, long ende)
	{
		return freieZimmerSuche(zimmertyp, start, ende, -1l);
	}
	
	
	@Transactional
	public Zimmer freieZimmerSuche(String zimmertyp, long start, long ende, long reservierung_id)
	{
		Zimmerkategorie zk = zimmerkategorieDao.findZimmerkategorieByZimmertyp(zimmertyp);
			
		if(zk != null)
		{	
			//wir schauen ob irgendein zimmer frei ist, das erste wird zurück gegeben
			for(Zimmer z:zk.getZimmer())
			{
				if(zimmerIstFrei(z, start, ende, reservierung_id))
				{
					return z;
				}
			}
		}
		
		return null;
	}
	
	public boolean zimmerIstFrei(Zimmer zimmer, long start, long ende, long reservierung_id)
	{
		//finden wir eine reservierung die überlappt?
		for(Reservierung r: zimmer.getReservierungen())
		{
			if(r.getId() == reservierung_id)
			{
				continue;
			}
			
			if(r.getStatus() == Status.Storniert)
			{
				continue;
			}
			
			//wir haben eine reservierung die mittem im Wunschtermin startet
			if(start > r.getStartdatum() && start < r.getEnddatum())
			{
				return false;
			}
			
			//wir haben eine reservierung die mittem im Wunschtermin endet
			if(ende > r.getStartdatum() && ende < r.getEnddatum())
			{
				return false;
			}
			
			//unser wunschtermin startet vor der reservierung und endet nach der reservierung
			if(start <= r.getStartdatum() && ende >= r.getEnddatum())
			{
				return false;
			}
		}
		//wir konnten keine Reservierung finden die überlappt, das Zimmer ist frei!
		return true;
	}

}

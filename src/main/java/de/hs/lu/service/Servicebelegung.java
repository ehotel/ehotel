package de.hs.lu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.hs.lu.model.ZusatzService;
import de.hs.lu.orm.dao.ReservierungsServiceDao;
import de.hs.lu.orm.dao.ZusatzServiceDao;

@Component
public class Servicebelegung {
	
	@Autowired
	private ReservierungsServiceDao rsDao;
	
	@Autowired
	private ZusatzServiceDao zusatzServiceDao;	
	
	public List<ZusatzService> freieServiceSuche(long start, long ende)
	{		
		List<ZusatzService> alleService = zusatzServiceDao.findAll();
		List<Object[]> gebuchteService = rsDao.findReservierungsServiceByStartAndEnd(start, ende);
		
		List<ZusatzService> freieService = alleService;
		
		for(Object[] row: gebuchteService)
		{
			for(ZusatzService zs: alleService)
			{
				if(row[0] == zs.getId())
				{
					//falls schon ausgebucht, entfernen
					if((Integer) row[1] >= zs.getAnzahl())
					{
						freieService.remove(zs);
						break;
					}
				}
			}
		}		
		return freieService;		
	}
}

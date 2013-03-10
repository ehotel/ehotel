package de.hs.lu.orm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.model.ReservierungsService;
import de.hs.lu.model.ZusatzService;
import de.hs.lu.orm.AbstractDao;

@Component("zusatzServiceDao")
public class ZusatzServiceDao extends AbstractDao<ZusatzService>{
	
	@Autowired
	private ReservierungsServiceDao rsDao;
	
	public ZusatzServiceDao(){
    	super(ZusatzService.class);
	}
	
    public List<ZusatzService> findAll(){
        return entityManager.createQuery("SELECT o FROM ZusatzService o", ZusatzService.class).getResultList();
    }
    
    @Transactional
    public void remove(ZusatzService zs)
    {
        for (; zs.getReservierungsServices().size() > 0; ) {
        	ReservierungsService rs = zs.getReservierungsServices().iterator().next();
            rsDao.remove(rsDao.getReference(rs.getId()));
            zs.getReservierungsServices().remove(rs);
        }
        super.remove(getReference(zs.getId()));
    }

}

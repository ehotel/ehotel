package de.hs.lu.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class ZimmerTest {
	
    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    ZimmerDao zimmerDao;
    
    @Autowired
    ZimmerkategorieDao zimmerkategorieDao;
    
   
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Transactional
	@Test
	@Rollback(false)
	public void test() {
	
		Zimmer z = new Zimmer();
		z.setZimmerNr(124);
		
		Zimmerkategorie zk = new Zimmerkategorie();		
		zk.setZimmertyp("einzelzimmer");
		zk.setPreis(55);
		zimmerkategorieDao.persist(zk);
		

		z.setZimmerkategorie(zk);
		assertEquals(z.getZimmerkategorie().getPreis(), 55f, 0);
		
		zimmerDao.persist(z);
		z.setZimmerNr(123);
		zimmerDao.merge(z);
		assertEquals(123,zimmerDao.findZimmerByZimmerNr(123).getZimmerNr());
		
		
		zimmerDao.remove(z);
		zimmerkategorieDao.remove(zk);	
				
	}
	
}

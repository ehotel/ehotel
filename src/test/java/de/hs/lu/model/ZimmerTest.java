package de.hs.lu.model;

import static org.junit.Assert.*;

import java.util.HashSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void test() {
		
		Zimmerkategorie zk = new Zimmerkategorie();		
		zk.setZimmertyp("einzelzimmer");
		zk.setPreis(55);
		zimmerkategorieDao.persist(zk);
		
		Zimmer z = new Zimmer();
		z.setZimmerNr(124);
		z.setZimmerkategorie(zk);
		z.setReservierungen(new HashSet<Reservierung>());
		assertEquals(z.getZimmerkategorie().getPreis(), 55f, 0);
		
		zimmerDao.persist(z);
		z.setZimmerNr(123);
		zimmerDao.merge(z);
		assertEquals(123,zimmerDao.findZimmerByZimmerNr(123).getZimmerNr());
		
		
		zimmerDao.remove(z);
		zimmerkategorieDao.remove(zk);	
				
	}
	
}

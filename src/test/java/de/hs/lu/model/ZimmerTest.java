package de.hs.lu.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class ZimmerTest {
	
    @PersistenceContext
    EntityManager entityManager;
    
   
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
		z.setZimmerNr(123);
		
		Zimmerkategorie zk = new Zimmerkategorie();
		
		zk.setZimmertyp("einzelzimmer");
		zk.setPreis(55);
		
		entityManager.persist(z);
		entityManager.persist(zk);
		
		z.setZimmerNr(124);
		z.setZimmerkategorie(zk);
		entityManager.merge(z);
		entityManager.flush();
		
		assertEquals(z.getZimmerkategorie().getPreis(), 55f, 0);		
		
		entityManager.remove(zk);		
		entityManager.remove(z);		
	}
}

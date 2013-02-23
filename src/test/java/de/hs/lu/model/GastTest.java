package de.hs.lu.model;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class GastTest {
	
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
	public void test() {
	
		Gast gast = new Gast();		
		gast.setBenutzername("testbenutzer");
		gast.setNachname("Müller");
		gast.setVorname("Mario");
		
		entityManager.persist(gast);
		entityManager.remove(gast);
				
	}

}

package de.hs.lu.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.orm.dao.BewertungDao;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class ZimmerkategorieTest {
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	@Autowired
	private ZimmerkategorieDao zimmerkategorieDao;
	
	@Autowired
	private BewertungDao bewertungDao;
	
	private Zimmerkategorie zk = new Zimmerkategorie();
	
	private Zimmer z = new Zimmer();
	
	private Bewertung b = new Bewertung();

	@Before
	@Transactional
	public void setUp() throws Exception {
		
		//Zimmerkategorie zk = new Zimmerkategorie();
		zk.setZimmertyp("einzelzimmer");		
		zimmerkategorieDao.persist(zk);
		

		//Zimmer z = new Zimmer();
		z.setZimmerNr(667);
		z.setZimmerkategorie(zk);
		zimmerDao.persist(z);
		
		//Bewertung b = new Bewertung();
		b.setBewertungspunkte(5);
		b.setText("best zimmer ever");
		b.setZimmerkategorie(zk);
		bewertungDao.persist(b);
		bewertungDao.flush();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Transactional
	@Test
	public void test() {
		
		Zimmerkategorie zk2 = zimmerkategorieDao.findById(zk.getId());
		
		assertNotNull(zk2);
		assertEquals(b.getId(), zk2.getBewertungen().iterator().next().getId());
		assertEquals(z.getId(), zk2.getZimmer().iterator().next().getId());		
	
	}

}

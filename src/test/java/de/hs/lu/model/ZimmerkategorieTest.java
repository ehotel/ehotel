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

import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class ZimmerkategorieTest {
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	@Autowired
	private ZimmerkategorieDao zimmerkategorieDao;

	private Zimmerkategorie zk = new Zimmerkategorie();
	
	private Zimmer z = new Zimmer();
	
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
		zimmerDao.flush();

		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Transactional
	@Test
	public void test() {
		
		Zimmerkategorie zk2 = zimmerkategorieDao.findById(zk.getId());
		
		assertNotNull(zk2);
		assertEquals(z.getId(), zk2.getZimmer().iterator().next().getId());
	
	}

}

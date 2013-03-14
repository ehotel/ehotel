package de.hs.lu.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.orm.dao.BewertungDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class BewertungTest {
	
	@Autowired
	BewertungDao bewertungDao;
	

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
			
		Bewertung b = new Bewertung();
		b.setBewertungspunkte(4);
		b.setDatum(System.currentTimeMillis());
		b.setText("Das Hotel war super, einsame spitze!");		
		bewertungDao.persist(b);
		
		Bewertung b2 = new Bewertung();
		b2.setBewertungspunkte(2);
		b2.setDatum(System.currentTimeMillis());
		b2.setText("Das Hotel hat mir nicht gefallen!");
		bewertungDao.persist(b2);
		
		Bewertung b3 = new Bewertung();
		b3.setBewertungspunkte(5);
		b3.setDatum(System.currentTimeMillis());
		b3.setText("Das Hotel hat mir super gefallen!");
		bewertungDao.persist(b3);
				
	}

}

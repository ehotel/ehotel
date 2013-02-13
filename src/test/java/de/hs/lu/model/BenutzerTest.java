package de.hs.lu.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.hs.lu.orm.dao.AdminDao;
import de.hs.lu.orm.dao.GastDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/META-INF/spring/applicationContext*.xml")
public class BenutzerTest {
	
	@Autowired
	private GastDao gastDao;
	
	@Autowired
	private AdminDao adminDao;


	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Rollback(false)
	@Transactional
	@Test
	public void test() {
		
		String password = "hierstehtwas";
		
		Gast g = new Gast();	
		g.setPassword(password);
		gastDao.persist(g);		
		assertTrue(!password.equalsIgnoreCase(g.getPassword()));
		
		Admin a = new Admin();
		a.setPassword(password);
		adminDao.persist(a);		
		assertTrue(g.getPassword().equalsIgnoreCase(a.getPassword()));		
		assertEquals(g.getPassword(), Benutzer.md5Hash(password));
		
		assertEquals(gastDao.findById(g.getId()).getPassword(), Benutzer.md5Hash(password));
				
	}
	
	@Test
	public void hash()
	{
		System.out.println(Benutzer.md5Hash("1234"));
	}

}

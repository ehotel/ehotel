package de.hs.lu.service;

import org.junit.After;
import org.junit.Before;

public class MailSenderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

//	@Test
	public void test() {
		MailSender.sendMail("emailadresse" , "emailadresse", "text");
	}

}

package de.hs.lu.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.orm.dao.BewertungDao;
import de.hs.lu.orm.dao.GastDao;
import de.hs.lu.orm.dao.ReservierungDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BewertungController {
	
	private static final Logger logger = LoggerFactory.getLogger(BewertungController.class);
	
	@Autowired
	private BewertungDao bwDao;
	
	@Autowired
	private GastDao gastDao;
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	@Autowired
	private ReservierungDao reservierungDao;

	
	@RequestMapping(value = "/bewertung/anlegen", method = RequestMethod.GET)
	public String bewertung(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		
		model.addAttribute("meldung", "Login fehlgeschlagen");
		
		return "bewertung_anlegen";
	}
	
}

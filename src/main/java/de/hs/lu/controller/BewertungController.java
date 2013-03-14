package de.hs.lu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

//import de.hs.lu.model.Gast;
//import de.hs.lu.model.Reservierung;
//import de.hs.lu.model.Zimmer;
import de.hs.lu.model.Bewertung;

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

	
	@RequestMapping(value = "/bewertung/anlegen", method = RequestMethod.POST)
	public String bewertung_anlegen(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		
		return "bewertung_anlegen";
	}
	
	@RequestMapping(value = "/bewertung/erstellen", method = RequestMethod.POST)
	public String bewertung_erstellen(Model model, HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		
		String r_id = (String) request.getParameter("reservierung_id");
		String username = authentication.getName();
		String bw_text = request.getParameter("text");
		int bw_punkte = Integer.parseInt(request.getParameter("punkte"));
		
		
		Bewertung bw = new Bewertung();
		bw.setGast(gastDao.findGastByBenutzername(username));
		bw.setBewertungspunkte(bw_punkte);
		bw.setText(bw_text);
		bw.setDatum(System.currentTimeMillis());
		model.addAttribute("meldung", "Reservierungs ID:" + r_id);
		return "meldung";
	}
	
	
	@RequestMapping(value = "/testimonials", method = RequestMethod.GET)
	public String testimonials(Model model) {
		
		model.addAttribute("bewertungsliste", bwDao.findAll());	
				
		return "testimonials";
	}
	

}

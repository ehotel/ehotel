package de.hs.lu.controller;


import javax.servlet.http.HttpServletRequest;

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
	private ReservierungDao reservierungDao;

	
	@RequestMapping(value = "/bewertung/anlegen", method = RequestMethod.POST)
	public String bewertung_anlegen(Model model, HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		String id = request.getParameter("reservierung_id");
		model.addAttribute("reservierung_id", id);
		
		return "bewertung_anlegen";
	}
	
	@RequestMapping(value = "/bewertung/erstellen", method = RequestMethod.POST)
	public String bewertung_erstellen(Model model, HttpServletRequest request) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		
		Long r_id = Long.parseLong(request.getParameter("reservierung_id"));
		String username = authentication.getName();
		String bw_text = request.getParameter("bw_text");
		int bw_punkte = Integer.parseInt(request.getParameter("punkte"));
		
		
		Bewertung bw = new Bewertung();
		bw.setGast(gastDao.findGastByBenutzername(username));
		bw.setBewertungspunkte(bw_punkte);
		bw.setText(bw_text);
		bw.setDatum(System.currentTimeMillis());
		bw.setReservierung(reservierungDao.findById(r_id));
		
		bwDao.persist(bw);
		bw = bwDao.merge(bw);
		logger.info("Reservierungs ID:" + r_id + "BewertungsID: " + bw.getId() + bw.getText());
		return "redirect:../testimonials";
	}

	
	
	@RequestMapping(value = "/testimonials", method = RequestMethod.GET)
	public String testimonials(Model model) {
		
		model.addAttribute("bewertungsliste", bwDao.findAll());	
				
		return "testimonials";
	}
	

}

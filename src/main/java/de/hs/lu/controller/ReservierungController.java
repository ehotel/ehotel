package de.hs.lu.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.model.Reservierung;
import de.hs.lu.model.Zimmer;
import de.hs.lu.orm.dao.ReservierungDao;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;
import de.hs.lu.service.Zimmerbelegung;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ReservierungController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservierungController.class);
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	@Autowired
	private ReservierungDao reservierungDao;
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	@Autowired
	private Zimmerbelegung belegung;
	
	@RequestMapping(value = "/freie_zimmer_suche", method = RequestMethod.GET)
	public String anlegen(Model model) {
		
		model.addAttribute("modus", "create");
		model.addAttribute("zimmerkategorien", zkDao.findAll());
				
		return "freie_zimmer_suche";
	}
	
	@RequestMapping(value = "/zimmer_suche", method = RequestMethod.POST)
	public String suche(Model model, HttpServletRequest request) throws ParseException {
		
		String zimmerkategorie = (String) request.getParameter("zk_typ");
		String anreise_s = (String) request.getParameter("anreise");
		String abreise_s = (String) request.getParameter("abreise");
		
    	DateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");		
		Date anreise = formatter.parse(anreise_s);
		Date abreise = formatter.parse(abreise_s);		
				
		Zimmer z = belegung.freieZimmerSuche(zimmerkategorie, anreise.getTime(), abreise.getTime());
		if(z != null)
		logger.info("zimmerid: " + z.getId().toString());
		
		
		model.addAttribute("anreise", anreise_s);
		model.addAttribute("abreise", abreise_s);
		model.addAttribute("zimmer", z);
				
		return "freie_zimmer_liste";
	}
	
	
	@RequestMapping(value = "/reservieren", method = RequestMethod.POST)
    public String reservieren(Model model, HttpServletRequest request) throws ParseException {
		
		String id = (String) request.getParameter("zimmer_id");
		String anreise_s = (String) request.getParameter("anreise");
		String abreise_s = (String) request.getParameter("abreise");
		
		DateFormat formatter = new SimpleDateFormat("dd.mm.yyyy");		
		Date anreise = formatter.parse(anreise_s);
		Date abreise = formatter.parse(abreise_s);
		
		Zimmer z = zimmerDao.findById(Long.parseLong(id));
		
		logger.info(anreise_s);
		logger.info(abreise_s);
		
		Reservierung r = new Reservierung();
		r.setStartdatum(anreise.getTime());
		r.setEnddatum(abreise.getTime());
		r.setZimmer(z);
		
		reservierungDao.persist(r);		
		model.addAttribute("meldung", "Reservierung wurde angelegt");
				
		return "meldung";
	}
	
}

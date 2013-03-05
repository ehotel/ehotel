package de.hs.lu.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hs.lu.model.Gast;
import de.hs.lu.model.Reservierung;
import de.hs.lu.model.ReservierungsService;
import de.hs.lu.model.Status;
import de.hs.lu.model.Zimmer;
import de.hs.lu.orm.dao.GastDao;
import de.hs.lu.orm.dao.ReservierungDao;
import de.hs.lu.orm.dao.ReservierungsServiceDao;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;
import de.hs.lu.orm.dao.ZusatzServiceDao;
import de.hs.lu.service.Servicebelegung;
import de.hs.lu.service.Zimmerbelegung;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes({"reservierung_id", "abreise" , "anreise", "min", "max"})
public class ReservierungController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservierungController.class);
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	@Autowired
	private ZusatzServiceDao zsDao;
	
	@Autowired
	private ReservierungsServiceDao rsDao;
	
	@Autowired
	private ReservierungDao reservierungDao;
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	@Autowired
	private GastDao gastDao;
	
	@Autowired
	private Zimmerbelegung belegung;
	
	@Autowired
	private Servicebelegung servicebelegung;
	
	
	@RequestMapping(value = "/freie_zimmer_suche", method = RequestMethod.GET)
	public String anlegen(Model model) {
		
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		
		String username = authentication.getName();				
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
		r.setGast(gastDao.findGastByBenutzername(username));
		r.setStatus(Status.Aktiv);
		
		reservierungDao.persist(r);
		r = reservierungDao.merge(r);
		model.addAttribute("meldung", "Reservierung wurde angelegt <br/> Möchten Sie einen ZusatzService <a href=\"freie_services_suche\">buchen</a>?");
			
        Calendar calendar_anreise = Calendar.getInstance();
        calendar_anreise.setTime(anreise);
        
        Calendar calendar_abreise = Calendar.getInstance();
        calendar_abreise.setTime(abreise);
        
        logger.info(String.valueOf(calendar_anreise.get(Calendar.YEAR)));
        logger.info(String.valueOf(calendar_anreise.get(Calendar.MONTH)));
        logger.info(String.valueOf(calendar_anreise.get(Calendar.DAY_OF_MONTH)));
        logger.info("reservierung_id: " + r.getId());
                
		//dieses variablen sind session_attribute!
		model.addAttribute("reservierung_id", r.getId());
		model.addAttribute("anreise", anreise.getTime());
		model.addAttribute("abreise", abreise.getTime());
		
				
		return "meldung";
	}
	
	@RequestMapping(value = "/freie_services_suche", method = RequestMethod.GET)
	public String services_suche(Model model) {
		
		long anreise_l = (Long) model.asMap().get("anreise");
		long abreise_l = (Long) model.asMap().get("abreise");
		
		Date anreise = new Date(anreise_l);
		Date abreise = new Date(abreise_l);
		
		logger.info("suche für anreise: " + anreise_l + " services");
		logger.info("suche für abreise: " + abreise_l + " services");
		
		model.addAttribute("zusatzservices", servicebelegung.freieServiceSuche(anreise.getTime(), abreise.getTime()));		
				
		return "freie_services_suche";
	}
	
	@RequestMapping(value = "/service_reservieren", method = RequestMethod.POST)
    public String service_reservieren(Model model, HttpServletRequest request) throws ParseException {
		
		ReservierungsService rs = new ReservierungsService();
		rs.setStartdatum((Long) model.asMap().get("anreise"));
		rs.setEnddatum((Long)model.asMap().get("abreise"));
		
		Long reservierungs_id = (Long) (model.asMap().get("reservierung_id"));
		
		rs.setReservierung(reservierungDao.findById(reservierungs_id));
		
		long zusatz_id = Long.valueOf(request.getParameter("service"));		
		rs.setZusatzService(zsDao.findById(zusatz_id));
		rsDao.persist(rs);			

		model.addAttribute("meldung", "ZusatzService wurde reserviert");					
				
		return "meldung";
	}
	
	@RequestMapping(value = "/reservierung/liste", method = RequestMethod.GET)
	public String reservierung_liste(Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Gast g = gastDao.findGastByBenutzername(username);
		
		model.addAttribute("reservierungsliste", rsDao.findReservierungenByGast(g));
		
		return "reservierung_auflisten";
	}

	
}

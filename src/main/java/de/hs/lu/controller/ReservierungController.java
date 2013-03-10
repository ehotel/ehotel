package de.hs.lu.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import de.hs.lu.model.Gast;
import de.hs.lu.model.Reservierung;
import de.hs.lu.model.ReservierungsService;
import de.hs.lu.model.Status;
import de.hs.lu.model.Zimmer;
import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.model.ZusatzService;
import de.hs.lu.orm.dao.GastDao;
import de.hs.lu.orm.dao.ReservierungDao;
import de.hs.lu.orm.dao.ReservierungsServiceDao;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;
import de.hs.lu.orm.dao.ZusatzServiceDao;
import de.hs.lu.service.Servicebelegung;
import de.hs.lu.service.Zimmerbelegung;
import de.hs.lu.service.MailSender;

@Controller
@SessionAttributes({"reservierung_id", "abreise" , "anreise", "min", "max", "username"})
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
	public String zimmer_suche(Model model, HttpServletRequest request) throws ParseException {
		
		String zimmerkategorie = (String) request.getParameter("zk_typ");
		String anreise_s = (String) request.getParameter("anreise");
		String abreise_s = (String) request.getParameter("abreise");
		
    	DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");		
		Date anreise = formatter.parse(anreise_s);
		Date abreise = formatter.parse(abreise_s);
		
		List<Zimmer> zimmerliste = new ArrayList<Zimmer>();	
				
		if(zimmerkategorie.equals("egal"))
		{
			List<Zimmerkategorie> zk_liste = zkDao.findAll();
			for(Zimmerkategorie zk: zk_liste)
			{
				Zimmer z = belegung.freieZimmerSuche(zk.getZimmertyp(), anreise.getTime(), abreise.getTime());
				if(z != null)
				{
					zimmerliste.add(z);
				}				
			}
		}
		else
		{
			Zimmer z = belegung.freieZimmerSuche(zimmerkategorie, anreise.getTime(), abreise.getTime());
			zimmerliste.add(z);			
		}	
		
		model.addAttribute("gaesteliste", gastDao.findAll());
		model.addAttribute("anreise", anreise_s);
		model.addAttribute("abreise", abreise_s);
		model.addAttribute("zimmerliste", zimmerliste);
				
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
		String user = (String) request.getParameter("user");
		
		if(username.equals("admin"))
		{
			username = user;
		}
		
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");		
		Date anreise = formatter.parse(anreise_s);
		Date abreise = formatter.parse(abreise_s);
		
		Zimmer z = zimmerDao.findById(Long.parseLong(id));
		
		logger.info(anreise_s);
		logger.info(abreise_s);
				
		Gast gast = gastDao.findGastByBenutzername(username);
		
		Reservierung r = new Reservierung();
		r.setStartdatum(anreise.getTime());
		r.setEnddatum(abreise.getTime());
		r.setZimmer(z);
		r.setGast(gast);
		r.setStatus(Status.Aktiv);
		
		reservierungDao.persist(r);
		r = reservierungDao.merge(r);
		model.addAttribute("meldung", "Reservierung wurde angelegt <br/> Moechten Sie einen ZusatzService <a href=\"freie_services_suche\">buchen</a>?");
		
        String bestaetigung = "Sie haben soeben eine Zimmer reserviert, die Details dazu kï¿½nnen Sie im ehotel-System nachschauen";        
        MailSender.sendMail(gast.getEmail(), "no-reply@ehotel-arno.com", bestaetigung);		
			
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
		model.addAttribute("username", username);
				
		return "meldung";
	}
	
	@RequestMapping(value = "/freie_services_suche", method = RequestMethod.GET)
	public String services_suche(Model model) {
		
		long anreise_l = (Long) model.asMap().get("anreise");
		long abreise_l = (Long) model.asMap().get("abreise");		
		
		DateFormat formatter = new SimpleDateFormat("yyyy,MM,dd");		
		Date min = new Date(anreise_l);
		Date max = new Date(abreise_l);
		
		String min_s = formatter.format(min);
		String max_s = formatter.format(max);
		
		//dieses variablen sind session_attribute!
		model.addAttribute("min" , min_s);
		model.addAttribute("max" , max_s);
				
		model.addAttribute("zusatzservices", servicebelegung.freieServiceSuche(anreise_l, abreise_l));		
				
		return "freie_services_suche";
	}
	
	@RequestMapping(value = "/freie_services_suche_extra", method = RequestMethod.POST)
	public String services_suche_extra(Model model, HttpServletRequest request) {
		
		String id_s = (String) request.getParameter("reservierung_id");
		Reservierung r = reservierungDao.findById(Long.parseLong(id_s));
		
		model.addAttribute("reservierung_id", r.getId());
		model.addAttribute("anreise", r.getStartdatum());
		model.addAttribute("abreise", r.getEnddatum());
		model.addAttribute("username", r.getGast().getBenutzername());
		
		
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		Date min = new Date(r.getStartdatum());
		Date max = new Date(r.getEnddatum());
		
		String min_s = formatter.format(min);
		String max_s = formatter.format(max);
		
		model.addAttribute("min" , min_s);
		model.addAttribute("max" , max_s);
		
		model.addAttribute("zusatzservices", servicebelegung.freieServiceSuche(r.getStartdatum(), r.getEnddatum()));
				
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
		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		String username = authentication.getName();
		
		String username = (String) model.asMap().get("username");
		
		Gast gast = gastDao.findGastByBenutzername(username);
		
        String bestaetigung = "Sie haben soeben eine ZusatService reserviert, die Details dazu kÃ¶nnen Sie im ehotel-System nachschauen";
        MailSender.sendMail(gast.getEmail(), "no-reply@ehotel-arno.com", bestaetigung);

		model.addAttribute("meldung", "ZusatzService wurde reserviert");					
				
		return "meldung";
	}
	
	@RequestMapping(value = "/reservierung/liste", method = RequestMethod.GET)
	public String reservierung_liste(Model model) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Gast g = gastDao.findGastByBenutzername(username);		
		model.addAttribute("reservierungsliste", reservierungDao.findReservierungenByGast(g));
		model.addAttribute("username", g.getBenutzername());
		
		return "reservierung_auflisten";
	}
	
	@RequestMapping(value = "/reservierung/stornieren/{id}", method = RequestMethod.POST)
	public String reservierung_stornieren(@PathVariable("id") Long id, Model model) {
		
		model.asMap().clear();
		
		Reservierung reservierung = reservierungDao.findById(id);
		reservierung.setStatus(Status.StornierungErwuenscht);
		reservierungDao.merge(reservierung);
		
		return "redirect:admin/reservierung/liste";
	}
	
	@RequestMapping(value = "/reservierung/aendern/{id}", method = RequestMethod.POST)
	public String reservierung_aendern(@PathVariable("id") Long id, Model model) {
		
		
		Reservierung r = reservierungDao.findById(id);
		//model.asMap().clear();
		
		//model.addAttribute("reservierung_id", r.getId());
		model.addAttribute("reservierung", r);
		//model.addAttribute("anreise", r.getStartdatum());
		//model.addAttribute("abreise", r.getEnddatum());
		
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		
		Date min = new Date(r.getStartdatum());
		Date max = new Date(r.getEnddatum());
		
		String min_s = formatter.format(min);
		String max_s = formatter.format(max);
		
		model.addAttribute("min" , min_s);
		model.addAttribute("max" , max_s);
		model.addAttribute("zimmerkategorieliste", zkDao.findAll());
		model.addAttribute("zimmerkategorie", r.getZimmer().getZimmerkategorie().getZimmertyp());
		List<ReservierungsService> serviceListe =  rsDao.findReservierungsServiceByReservierung(r);		
		model.addAttribute("reservierungserviceliste", serviceListe);		
		
		return "reservierung_aendern";
	}
	
	@RequestMapping(value = "reservierung/update", method = RequestMethod.POST)
    public String reservierung_update(@Valid Reservierung reservierung, BindingResult bindingResult, Model model, HttpServletRequest request) throws ParseException {
		
		//1. über die id das datenbankobjekt holen
		Reservierung r = reservierungDao.findById(reservierung.getId());
		
		//String id_s = (String) request.getParameter("id");                        funktioniert nicht ohne BindingResult
		//Reservierung r = reservierungDao.findById(Long.parseLong(id_s));			
		String typ = request.getParameter("zk_typ");
		String start =(String) request.getParameter("startdatum");
		String end =(String) request.getParameter("enddatum");
		
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");		
		Date anreise = formatter.parse(start);
		Date abreise = formatter.parse(end);
		
		logger.info(start);
		logger.info(typ);
		logger.info(String.valueOf(anreise));
		logger.info(String.valueOf(abreise));
		Zimmer z = belegung.freieZimmerSuche(typ, anreise.getTime(), abreise.getTime());

		if(z != null)
		{
			//2. vom datenbankobjekt die neuen start-end-datum setztn
			r.setStartdatum(anreise.getTime());
			r.setEnddatum(abreise.getTime());
			r.setZimmer(z);	
			
			//reservierungDao.persist(r);
			r = reservierungDao.merge(r);
			        	        	        
			model.asMap().clear();
			model.addAttribute("meldung", "Reservierung wurde geaendert");
					
			return "meldung";
		}	
		else
		{
			model.asMap().clear();
			model.addAttribute("meldung", "Zimmertyp zum gewählten Datum nicht verfügbar");
					
			return "meldung";
		}
		
	}
	
	@RequestMapping(value = "/reservierung/details/{id}", method = RequestMethod.POST)
	public String reservierung_details(@PathVariable("id") Long id, Model model) {
		
		Reservierung reservierung = reservierungDao.findById(id);
		model.addAttribute(reservierung);
		
		List<ReservierungsService> serviceListe =  rsDao.findReservierungsServiceByReservierung(reservierung);		
		model.addAttribute("reservierungserviceliste", serviceListe);

		return "reservierung_details";
	}
	
	@RequestMapping(value = "/reservierung/loeschen/{id}", method = RequestMethod.POST)
	public String reservierung_loeschen(@PathVariable("id") Long id, Model model) {
		
		reservierungDao.remove(reservierungDao.findById(id));
		reservierungDao.flush();
		
		return "redirect:/admin/reservierung/liste";
	}
	
	@RequestMapping(value = "reservierung/reservierungservice/loeschen", method = RequestMethod.POST)
	public String reservierungservice_loeschen(Model model, HttpServletRequest request) {
		
		String id_s = (String) request.getParameter("service_id");
		long id = Long.parseLong(id_s);		
		ReservierungsService service = rsDao.findById(id);
		
		long reservierung_id = service.getReservierung().getId();		
		rsDao.remove(rsDao.getReference(id));
		rsDao.flush();
		
		Reservierung reservierung = reservierungDao.findById(reservierung_id);
		model.addAttribute(reservierung);
		
		List<ReservierungsService> serviceListe =  rsDao.findReservierungsServiceByReservierung(reservierung);		
		model.addAttribute("reservierungserviceliste", serviceListe);

		return "reservierung_details";
	}
	
	
	@RequestMapping(value = "/admin/reservierung/liste", method = RequestMethod.GET)
	public String admin_reservierung_liste(Model model) {
		
		model.addAttribute("reservierungsliste", reservierungDao.findAll());
		
		return "reservierung_auflisten_admin";
	}
	
	@RequestMapping(value = "admin/reservierung/stornieren/{id}", method = RequestMethod.POST)
	public String admin_reservierung_stornieren(@PathVariable("id") Long id, Model model) {
		
		model.asMap().clear();
		
		Reservierung reservierung = reservierungDao.findById(id);
		reservierung.setStatus(Status.Storniert);
		reservierungDao.merge(reservierung);		
		
		return "redirect:/admin/reservierung/liste";
	}
	

	
}

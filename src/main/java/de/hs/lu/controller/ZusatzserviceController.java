package de.hs.lu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.model.ReservierungsService;
import de.hs.lu.model.ZusatzService;
import de.hs.lu.orm.dao.ReservierungsServiceDao;
import de.hs.lu.orm.dao.ZusatzServiceDao;

/**
 * Handles requests for the application home page.
 */
@RequestMapping("/zusatzservice")
@Controller
public class ZusatzserviceController {
	
	@Autowired
	private ZusatzServiceDao zsDao;
	
	@Autowired
	private ReservierungsServiceDao rsDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ZusatzserviceController.class);

	@RequestMapping(value = "/erstellen", method = RequestMethod.POST)
	public String erstellen(@Valid ZusatzService zusatzservice, BindingResult bindingResult, Model model) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Zusatzservice");
            return "meldung_admin";
        }
        
        zsDao.persist(zusatzservice);
		
		model.addAttribute("meldung", "zusatzservice wurde erfoglreich angelegt");
		
		return "meldung_admin";
	}
	
	@RequestMapping(value = "/anlegen", method = RequestMethod.GET)
	public String anlegen(Model model) {
		
		model.addAttribute("modus", "create");
				
		return "zusatzservice_anlegen";
	}
	
	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public String listen(Model model) {
		
		model.asMap().clear();
		model.addAttribute("zsliste", zsDao.findAll());
				
		return "zusatzservice_auflisten";
	}
	
	
	
	@RequestMapping(value = "/editieren/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
		
		model.asMap().clear();
		model.addAttribute("modus", "edit");
		model.addAttribute("zs", zsDao.findById(id));
				
		return "zusatzservice_anlegen";
	}
	
	@RequestMapping(value = "/aendern", method = RequestMethod.POST)
    public String aendern(@Valid ZusatzService zusatzservice, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Zusatzservices");
            return "meldung_admin";
        }
        
        logger.info(zusatzservice.getId().toString());
        
        zsDao.merge(zusatzservice);
        
		model.asMap().clear();
		model.addAttribute("meldung", "zusatzservice wurde geändert");
				
		return "meldung_admin";
	}
	
	@RequestMapping(value = "/loeschen/{id}", method = RequestMethod.GET)
    public String loeschen(@PathVariable("id") Long id, Model model) {
		
		ZusatzService zs = zsDao.findById(id);
		
		List<ReservierungsService> rslist =  rsDao.findReservierungsServiceByZusatzService(zs);	
		
		for(ReservierungsService rs: rslist)
		{
			if(rs.getEnddatum() > System.currentTimeMillis())
			{
				model.addAttribute("meldung", "zusatzservice kann nicht gelöscht werden da in der zukunft noch gebucht");				
				return "meldung_admin";				
			}
		}		
				
		model.asMap().clear();				
		
		zsDao.remove(zs);		
		
		model.addAttribute("meldung", "zusatzservice wurde erfolgreich gelöscht");				
		return "meldung_admin";
	}
	
}

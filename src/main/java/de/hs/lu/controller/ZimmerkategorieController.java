package de.hs.lu.controller;

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

import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

/**
 * Handles requests for the application home page.
 */
@RequestMapping("/zimmerkategorie")
@Controller
public class ZimmerkategorieController {
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ZimmerkategorieController.class);

	@RequestMapping(value = "/erstellen", method = RequestMethod.POST)
	public String erstellen(@Valid Zimmerkategorie zimmerkategorie, BindingResult bindingResult, Model model) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding der Zimmerskategorie");
            return "meldung_admin";
        }
        
        zkDao.persist(zimmerkategorie);
		
		model.addAttribute("meldung", "zimmerkategorie wurde erfoglreich angelegt");
		
		return "meldung_admin";
	}
	
	@RequestMapping(value = "/anlegen", method = RequestMethod.GET)
	public String anlegen(Model model) {
		
		model.addAttribute("modus", "create");
				
		return "zimmerkategorie_anlegen";
	}
	
	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public String listen(Model model) {
		
		model.asMap().clear();
		model.addAttribute("zimmerkategorieliste", zkDao.findAll());
				
		return "zimmerkategorie_auflisten";
	}
	
	
	
	@RequestMapping(value = "/editieren/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
		
		model.asMap().clear();
		model.addAttribute("modus", "edit");
		model.addAttribute("zk", zkDao.findById(id));
				
		return "zimmerkategorie_anlegen";
	}
	
	@RequestMapping(value = "/aendern", method = RequestMethod.POST)
    public String aendern(@Valid Zimmerkategorie zimmerkategorie, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Zimmers");
            return "meldung_admin";
        }
        
        logger.info(zimmerkategorie.getId().toString());
        
        zkDao.merge(zimmerkategorie);
        
		model.asMap().clear();
		model.addAttribute("meldung", "zimmerkategorie wurde geändert");
				
		return "meldung_admin";
	}
	
	@RequestMapping(value = "/loeschen/{id}", method = RequestMethod.GET)
    public String loeschen(@PathVariable("id") Long id, Model model) {
		
		model.asMap().clear();
		
		if(zimmerDao.findZimmerByZimmerkategorie(zkDao.findById(id)).size() > 0)
		{
			model.addAttribute("meldung", "zimmerkategorie kann nicht gelöscht werden da noch zimmer mit dieser kategorie existieren");
			return "meldung_admin";
		}
		
		Zimmerkategorie zk = zkDao.getReference(id);
		zkDao.remove(zk);		
		
		model.addAttribute("meldung", "zimmerkategorie wurde erfolgreich gelöscht");				
		return "meldung_admin";
	}
	
}

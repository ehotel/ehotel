package de.hs.lu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ZimmerkategorieController {
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ZimmerkategorieController.class);

	@RequestMapping(value = "/zimmerkategorieerstellen", method = RequestMethod.POST)
	public String home(@Valid Zimmerkategorie zimmerkategorie, BindingResult bindingResult, Model model) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding der Zimmerskategorie");
            return "meldung";
        }
        
        zkDao.persist(zimmerkategorie);
		
		model.addAttribute("meldung", "zimmerkategorie wurde erfoglreich angelegt");
		
		return "meldung";
	}

}

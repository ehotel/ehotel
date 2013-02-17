package de.hs.lu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.model.Zimmer;
import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ZimmerController {
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ZimmerController.class);

	@RequestMapping(value = "/zimmererstellen", method = RequestMethod.POST)
	public String home(@Valid Zimmer zimmer, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Zimmers");
            return "meldung";
        }
        
        String zk_id = request.getParameter("zk_id");
        Zimmerkategorie zk = zkDao.findById(Long.valueOf(zk_id));
        
        zimmer.setZimmerkategorie(zk);        
        zimmerDao.persist(zimmer);
		
		model.addAttribute("meldung", "zimmer wurde erfoglreich angelegt");
		
		return "meldung";
	}
	
	@RequestMapping(value = "/zimmer_anlegen", method = RequestMethod.GET)
	public String home(Model model) {
		
		fillForm(model);
				
		return "zimmer_anlegen";
	}
	
	
	
	public void fillForm(Model uiModel)
	{
		uiModel.asMap().clear();
		uiModel.addAttribute("zimmerkategorien", zkDao.findAll());
	}

}

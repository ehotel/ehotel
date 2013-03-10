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

import de.hs.lu.model.Reservierung;
import de.hs.lu.model.Zimmer;
import de.hs.lu.model.Zimmerkategorie;
import de.hs.lu.orm.dao.ReservierungDao;
import de.hs.lu.orm.dao.ZimmerDao;
import de.hs.lu.orm.dao.ZimmerkategorieDao;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/zimmer")
public class ZimmerController {
	
	@Autowired
	private ZimmerkategorieDao zkDao;
	
	@Autowired
	private ZimmerDao zimmerDao;
	
	@Autowired
	private ReservierungDao rsDao;
	
	private static final Logger logger = LoggerFactory.getLogger(ZimmerController.class);

	@RequestMapping(value = "/erstellen", method = RequestMethod.POST)
	public String home(@Valid Zimmer zimmer, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Zimmers");
            return "meldung_admin";
        }
        
        String zk_id = request.getParameter("zk_id");
        Zimmerkategorie zk = zkDao.findById(Long.valueOf(zk_id));        
        zimmer.setZimmerkategorie(zk);
        
        zimmerDao.persist(zimmer);
		
		model.addAttribute("meldung", "zimmer wurde erfoglreich angelegt");
		
		return "meldung_admin";
	}
	
	@RequestMapping(value = "/anlegen", method = RequestMethod.GET)
	public String anlegen(Model model) {
		
		model.addAttribute("modus", "create");
		model.addAttribute("zimmerkategorien", zkDao.findAll());
				
		return "zimmer_anlegen";
	}
	
	@RequestMapping(value = "/liste", method = RequestMethod.GET)
	public String listen(Model model) {
		
		model.asMap().clear();
		model.addAttribute("zimmerliste", zimmerDao.findAll());
				
		return "zimmer_auflisten";
	}
	
	@RequestMapping(value = "/editieren/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
		
		model.asMap().clear();
		model.addAttribute("modus", "edit");
		model.addAttribute("zimmerkategorien", zkDao.findAll());
		model.addAttribute("zimmer", zimmerDao.findById(id));
				
		return "zimmer_anlegen";
	}
	
	@RequestMapping(value = "/aendern", method = RequestMethod.POST)
    public String aendern(@Valid Zimmer zimmer, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Zimmers");
            return "meldung_admin";
        }
        
        logger.info(zimmer.getId().toString());
        
        String zk_id = request.getParameter("zk_id");
        Zimmerkategorie zk = zkDao.findById(Long.valueOf(zk_id));
        zimmer.setZimmerkategorie(zk);
        zimmerDao.merge(zimmer);
        
		model.asMap().clear();
		model.addAttribute("meldung", "zimmer wurde geändert");
				
		return "meldung_admin";
	}
	
	@RequestMapping(value = "/loeschen/{id}", method = RequestMethod.GET)
    public String loeschen(@PathVariable("id") Long id, Model model) {
		
				
		Zimmer z = zimmerDao.findById(id);
		List<Reservierung> rlist = rsDao.findReservierungenByZimmer(z);
		
		for(Reservierung r: rlist)
		{
			if(r.getEnddatum() > System.currentTimeMillis())
			{				
				model.addAttribute("meldung", "zimmer kann nicht gelöscht werden weil noch reservierungen in der zukunft liegen");				
				return "meldung_admin";				
			}
		}
		
		zimmerDao.remove(z);
		
		model.asMap().clear();
				
		return "redirect:/zimmer/liste";
	}


}

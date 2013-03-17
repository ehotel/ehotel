package de.hs.lu.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.orm.dao.ZimmerkategorieDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ZimmerkategorieDao zkDao;

	@RequestMapping(value = {"/" ,"/ehotel", "/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
			
		model.addAttribute("zimmerkategorien", zkDao.findAll());
		
		return "home";
	}
	
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginfailed(Model model) {
				
		model.addAttribute("meldung", "Login fehlgeschlagen");
		
		return "meldung";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
				
		model.addAttribute("meldung", "Sie haben sich erfolgreich ausgeloggt");
		
		return "meldung";
	}
	
	@RequestMapping(value = "/login_user", method = RequestMethod.GET)
	public String login(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null && authentication.isAuthenticated() && !authentication.getName().contentEquals("anonymousUser"))
		{
			logger.info(authentication.getName());
			model.addAttribute("meldung", "Sie sind bereits eingeloggt");
			return "meldung";
		}				
		
		return "login_user";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Model model) {
				
		model.addAttribute("meldung", "Waehlen Sie oben eines der Menupunkte aus!");
		
		return "meldung_admin";
	}
}

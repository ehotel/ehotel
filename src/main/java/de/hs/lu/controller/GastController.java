package de.hs.lu.controller;

import java.util.UUID;

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

import de.hs.lu.model.Gast;
import de.hs.lu.orm.dao.GastDao;
import de.hs.lu.service.MailSender;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GastController {
	
	private static final Logger logger = LoggerFactory.getLogger(GastController.class);
	
	@Autowired
	private GastDao gastDao;
	
	@RequestMapping(value = "/registrieren", method = RequestMethod.GET)
    public String registrieren(Model model) {
		
		model.addAttribute("modus", "create");
		
        return "registrieren";
	}
	
	@RequestMapping(value = "/profil", method = RequestMethod.GET)
    public String profil(Model model) {
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication == null || !authentication.isAuthenticated())
		{
			return "login";
		}
		
		String username = authentication.getName();
		Gast gast = gastDao.findGastByBenutzername(username);
		
		model.addAttribute("gast", gast);		
		model.addAttribute("modus", "edit");
		
        return "registrieren";
	}
	
	@RequestMapping(value = "/profilUpdate", method = RequestMethod.POST)
    public String update(@Valid Gast gast, BindingResult bindingResult, Model model, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	model.addAttribute("meldung" , "Fehler beim Binding des Gastes");
            return "meldung"; 
        }
        
    	model.addAttribute("gast", gast);
		model.addAttribute("modus", "edit");
        
        if(!gast.getEmail().matches("[a-z0-9A-Z_\\.]+@[a-z0-9A-Z]+\\.[a-zA-Z]+"))
        {
        	model.addAttribute("emailError", "E-Mail Syntax ist falsch");
        	return "registrieren";
        }
        
        
        Gast gast_temp = gastDao.findGastByEMail(gast.getEmail());
        Gast gast_alt = gastDao.findGastByBenutzername(gast.getBenutzername());
        
        if(gast_temp != null && gast_temp.getId() != gast_alt.getId())
        {
        	model.addAttribute("emailError", "E-Mail ist schon vergeben");
        	return "registrieren";
        }
        
        String p1 = (String) request.getParameter("password");
        String p2 = (String) request.getParameter("password2");
        
        if(!p1.equalsIgnoreCase(p2))
        {
        	model.addAttribute("passwordError", "Passwörter müssen übereinstimmen");
        	return "registrieren";
        }
        
        //alten und neuen gast mergen
        gast_alt.setVorname(gast.getVorname());
        gast_alt.setNachname(gast.getNachname());
        gast_alt.setEmail(gast.getEmail());
        if(!p1.isEmpty())
        {
        	gast_alt.setPassword(p1);
        }
        gastDao.merge(gast_alt);
        
        model.addAttribute("meldung", "Ihre Aenderungen wurden gespeichert");        
        return "meldung";
	}
	
	
	@RequestMapping(value = "/erzeugeGast", method = RequestMethod.POST)
    public String create(@Valid Gast gast, BindingResult bindingResult, Model uiModel, HttpServletRequest request) {
		
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("meldung" , "Fehler beim Binding des Gastes");
            return "meldung";
        }        
        HttpServletRequest r = request;
        
        uiModel.addAttribute("modus", "create");
        uiModel.addAttribute("gast", gast);
        
        
        if( gast.getVorname().isEmpty()||
        	gast.getNachname().isEmpty()||
        	gast.getEmail().isEmpty()||
        	gast.getBenutzername().isEmpty()||
        	gast.getPassword().isEmpty())
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("felderError", "Sie müssen alle Felder ausfüllen!<br/>");
        	return "registrieren";        	
        }
        
        if(gastDao.findGastByBenutzername(gast.getBenutzername()) != null)
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("benutzernameError", "Benutzername ist schon vergeben <br/>");
        	return "registrieren";
        }
        
        if(!gast.getEmail().matches("[a-z0-9A-Z_\\.]+@[a-z0-9A-Z]+\\.[a-zA-Z]+"))
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("emailError", "E-Mail Syntax ist falsch <br/>");
        	return "registrieren";
        }        
        
        if(gastDao.findGastByEMail(gast.getEmail()) != null)
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("emailError", "E-Mail ist schon vergeben <br/>");
        	return "registrieren";
        }
        
        String p1 = (String) r.getParameter("password");
        String p2 = (String) r.getParameter("password2");
        
        if(!p1.equalsIgnoreCase(p2) || p1.isEmpty())
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("passwordError", "Passwörter müssen übereinstimmen <br/>");
        	return "registrieren";
        }
        
        gast.setIstAktiviert(false);
        gast.setAktivierungsHash(Gast.md5Hash(gast.getEmail()));
        gast.setRechte("ROLE_USER");
        
        gastDao.persist(gast);
        gastDao.flush();
        
        //String bestaetigung = "Bitte hier klicken localhost:8080/ehotel-spring-mvc/aktivierung/" + gast.getAktivierungsHash();        
        //MailSender.sendMail(gast.getEmail(), "no-reply@ehotel-arno.com", bestaetigung);
        
        uiModel.addAttribute("meldung" , "Ihr Benutzer wurde erfolgreich registriert, checken sie ihre E-Mails");
        return "meldung";
	}
	
	public void fillForm(Model uiModel, Gast g)
	{
//		uiModel.asMap().clear();
//    	uiModel.addAttribute("vorname", g.getVorname());
//    	uiModel.addAttribute("nachname", g.getNachname());
//    	uiModel.addAttribute("benutzername", g.getBenutzername());
//    	uiModel.addAttribute("email", g.getEmail());
	}
	
	@RequestMapping(value = "/aktivierung/{hash}", method = RequestMethod.GET)
	public String gastAktivieren(@PathVariable("hash") String hash, Model uiModel)
	{
		Gast g = gastDao.findGastByAktivierungsHash(hash);
		if(g != null)
		{
			g.setIstAktiviert(true);
			gastDao.merge(g);
			uiModel.addAttribute("meldung", "Ihr Benutzer wurde aktiviert");
		}
		else
		{
			uiModel.addAttribute("meldung", "Ihr Benutzer konnte nicht aktiviert werden");
		}
		
		return "meldung";
	}
	
//	@RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login() {
//        return "login";
//	}
	
	@RequestMapping(value = "/einloggen", method = RequestMethod.POST)
    public String checklogin(Model model, HttpServletRequest request) {
		
		Gast g = gastDao.findGastByBenutzername(request.getParameter("benutzername"));
		if(g == null)
		{
			model.addAttribute("loginError", "Benutzername oder Password falsch");
			logger.debug("benutzer nicht gefunden");
			return "login";
		}
		
		Gast gastLogin = new Gast();
		gastLogin.setPassword(request.getParameter("password"));
		
		if(!g.getPassword().contentEquals(gastLogin.getPassword()))
		{
			model.addAttribute("loginError", "Benutzername oder Password falsch");
			logger.debug("pwd stimmt nicht überein");
			return "login";			
		}
		
		model.addAttribute("meldung", "Sie haben sich erfolgreich eingelogt");
		
		return "meldung";
	}
	
	@RequestMapping(value = "/gast/test", method = RequestMethod.GET)
	public String test(Model model) {
		
		model.addAttribute("meldung", "Das hier sehen nur angemeldete benutzer");
		
      return "meldung";
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String reset(Model model, HttpServletRequest request) {
				
		String username = (String) request.getParameter("username");		
		Gast gast = gastDao.findGastByBenutzername(username);		
		String temp_pwd = UUID.randomUUID().toString();
		temp_pwd = temp_pwd.replace("-", "");
		temp_pwd = temp_pwd.substring(0, 8);
		gast.setPassword(temp_pwd);
		gastDao.merge(gast);
		
        String bestaetigung = "Ihr neues Password lautet: " + temp_pwd;        
        MailSender.sendMail(gast.getEmail(), "no-reply@ehotel-arno.com", bestaetigung);
        logger.info("Neues Password ist: "  + temp_pwd);
		
		model.addAttribute("meldung", "Ihr neues Password wurde per Mail an Sie geschickt");
		
      return "meldung";
	}
}

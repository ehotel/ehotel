package de.hs.lu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
    public String blub() {
        return "registrieren";
	}	
	
	@RequestMapping(value = "/erzeugeGast", method = RequestMethod.POST)
    public String update(@Valid Gast gast, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
		
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("meldung" , "Fehler beim Binding des Gastes");
            return "meldung";
        }        
        HttpServletRequest r = httpServletRequest;
        
        
        if( gast.getVorname().isEmpty()||
        	gast.getNachname().isEmpty()||
        	gast.getEmail().isEmpty()||
        	gast.getBenutzername().isEmpty()||
        	gast.getPassword().isEmpty())
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("felderError", "Sie müssen alle Felder ausfüllen!");
        	return "registrieren";        	
        }
        
        if(gastDao.findGastByBenutzername(gast.getBenutzername()) != null)
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("benutzernameError", "Benutzername ist schon vergeben");
        	return "registrieren";
        }
        
        if(!gast.getEmail().matches("[a-z0-9A-Z_\\.]+@[a-z0-9A-Z]+\\.[a-zA-Z]+"))
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("emailError", "E-Mail Syntax ist falsch");
        	return "registrieren";
        }        
        
        if(gastDao.findGastByEMAil(gast.getEmail()) != null)
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("emailError", "E-Mail ist schon vergeben");
        	return "registrieren";
        }
        
        String p1 = (String) r.getParameter("password");
        String p2 = (String) r.getParameter("password2");
        
        if(!p1.equalsIgnoreCase(p2) || p1.isEmpty())
        {
        	fillForm(uiModel, gast);
        	uiModel.addAttribute("passwordError", "Passwörter müssen übereinstimmen");
        	return "registrieren";
        }
        
        gast.setIstAktviert(false);
        gast.setAktivierungsHash(Gast.md5Hash(gast.getEmail()));
        
        gastDao.persist(gast);
        gastDao.flush();
        
        String bestaetigung = "Bitte hier klicken localhost:8080/ehotel-spring-mvc/aktivierung/" + gast.getAktivierungsHash();
        
        MailSender.sendMail(gast.getEmail(), "no-reply@ehotel-arno.com", bestaetigung);
        
        uiModel.addAttribute("meldung" , "Ihr Benutzer wurde erfolgreich registriert, checken sie ihre E-Mails");
        return "meldung";
	}
	
	public void fillForm(Model uiModel, Gast g)
	{
		uiModel.asMap().clear();
    	uiModel.addAttribute("vorname", g.getVorname());
    	uiModel.addAttribute("nachname", g.getNachname());
    	uiModel.addAttribute("benutzername", g.getBenutzername());
    	uiModel.addAttribute("email", g.getEmail());
	}
	
	@RequestMapping(value = "/aktivierung/{hash}", method = RequestMethod.GET)
	public String gastAktivieren(@PathVariable("hash") String hash, Model uiModel)
	{
		Gast g = gastDao.findGastByAktivierungsHash(hash);
		if(g != null)
		{
			g.setIstAktviert(true);
			gastDao.merge(g);
			uiModel.addAttribute("meldung", "Ihr Benutzer wurde aktiviert");
		}
		else
		{
			uiModel.addAttribute("meldung", "Ihr Benutzer konnte nicht aktiviert werden");
		}
		
		return "meldung";
	}	
}

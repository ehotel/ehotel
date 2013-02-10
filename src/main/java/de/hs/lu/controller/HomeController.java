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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hs.lu.model.Gast;
import de.hs.lu.orm.dao.GastDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private GastDao gastDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String neueMethode(Locale locale, Model model) {
		logger.info("Welcome home! the client locale is "+ locale.toString());
				
		model.addAttribute("serverTime", "hier steht was");
		
		return "home";
	}
	
	@RequestMapping(value = "/gast", method = RequestMethod.GET)
    public String blub() {

        return "gast";
	}
	
	@RequestMapping(value = "/erzeugeGast", method = RequestMethod.POST)
    public String update(@Valid Gast gast, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	uiModel.addAttribute("serverTime", "falsch");
            return "home";
        }
        
        gastDao.persist(gast);
        gastDao.flush();
        
        
        uiModel.addAttribute("serverTime", "richtig");
        return "home";
	}
}

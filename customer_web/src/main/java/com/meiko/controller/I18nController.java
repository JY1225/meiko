package com.meiko.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@Controller
@RequestMapping("/i18n")
public class I18nController {
	//@Autowired 
	//private CookieLocaleResolver localeResolver;
	//@RequestMapping("/changeLocal")
    /*public ModelAndView changeLocal(HttpServletRequest request,String local,HttpServletResponse response){  
        if("zh".equals(local))  
            localeResolver.setLocale(request, response, Locale.CHINA);  
        else if("en".equals(local))  
            localeResolver.setLocale(request, response, Locale.ENGLISH);  
        return new ModelAndView("redirect:/");  
    }  */
}

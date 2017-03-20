package com.sooraj.springboot.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by SOORAJ on 11-12-2016.
 */
@Controller
public class WebApplicationController {
    
    private static final String welcomeMsg="WELCOME";

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", welcomeMsg);
		model.addObject("message", "This is welcome page!");
		model.setViewName("home");
		return model;
	}

    @RequestMapping(value = "/sadmin/page", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    public ModelAndView sAdminPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("title", welcomeMsg);
        model.addObject("message", "This is protected page - Super Admin Page!");
        model.setViewName("sadmin");
        return model;

    }

	@RequestMapping(value = "/admin/page", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", welcomeMsg);
		model.addObject("message", "This is protected page - Admin Page!");
		model.setViewName("admin");
		return model;

	}
	
	@RequestMapping(value = "/user/page", method = RequestMethod.GET)
	public ModelAndView dbaPage() {
		ModelAndView model = new ModelAndView();
		model.addObject("title", welcomeMsg);
		model.addObject("message", "This is protected page - User Page!");
		model.setViewName("user");
		return model;
	}

}
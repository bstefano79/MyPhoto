package it.bstefano79.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin()
	{
		return "admin";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}

}

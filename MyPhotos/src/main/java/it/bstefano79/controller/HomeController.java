package it.bstefano79.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String index()
	{
		return "index";
	}
	
	@RequestMapping("/hello/{name}")
	@ResponseBody
	public String Hello(@PathVariable String name)
	{
		return "Hello "+name;
	}
	
}

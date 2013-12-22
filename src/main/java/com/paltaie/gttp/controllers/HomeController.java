package com.paltaie.gttp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.paltaie.gttp.service.ThreadService;

@Controller
public class HomeController {
	
	@Autowired
	private ThreadService threadService;
	
	@RequestMapping("/index.*")
	public ModelAndView goHome() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
}

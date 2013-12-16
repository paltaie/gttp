package com.paltaie.gttp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cd.reddit.json.mapping.RedditLink;
import com.paltaie.gttp.service.ThreadService;

@Controller
public class ListingController {
	
	@Autowired
	private ThreadService threadService;
	
	@RequestMapping("/subreddit/{subreddit}")
	public ModelAndView getSubreddit(@PathVariable("subreddit") String subreddit) {
		ModelAndView mav = new ModelAndView("json/subreddit");
		List<RedditLink> listing = null;
		listing = threadService.getListing(subreddit, "hot");
		mav.addObject("listing", listing);
		return mav;
	}
}

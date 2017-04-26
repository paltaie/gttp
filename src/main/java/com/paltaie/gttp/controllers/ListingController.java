package com.paltaie.gttp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ListingController {

	@Autowired
	private ThreadService threadService;
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping("/subreddit/{subreddit}.*")
	public ModelAndView getSubreddit(@PathVariable("subreddit") String subreddit) throws JsonProcessingException {
		ModelAndView mav = new ModelAndView("json/subreddit");
		List<RedditLink> listing = threadService.getListing(subreddit, "hot");
		mav.addObject("string", objectMapper.writeValueAsString(listing));
		return mav;
	}
}

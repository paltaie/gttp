package com.paltaie.gttp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.service.ThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ListingController {

	private ThreadService threadService;

	@Autowired
	public ListingController(ThreadService threadService) {
		this.threadService = threadService;
	}

	@RequestMapping(value = "/subreddit/{subreddit}.*")
	@ResponseBody
	public List<RedditLink> getSubreddit(@PathVariable("subreddit") String subreddit) throws JsonProcessingException {
		return threadService.getListing(subreddit, "hot");
	}
}

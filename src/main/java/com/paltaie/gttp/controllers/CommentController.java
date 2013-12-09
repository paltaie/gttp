package com.paltaie.gttp.controllers;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cd.reddit.json.mapping.RedditComment;
import com.paltaie.gttp.service.CommentService;

@Controller
@RequestMapping
public class CommentController {
	
	private CommentService commentService;
	
	@Inject
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@RequestMapping("/topComment")
	public ModelAndView getTopComment(@RequestParam("subreddit") String subreddit, @RequestParam("threadId") String threadId) {
		ModelAndView mav = new ModelAndView("topComment");
		RedditComment topComment = commentService.getTopComment(subreddit, threadId);
		mav.addObject("topComment", topComment);
		return mav;
	}
}
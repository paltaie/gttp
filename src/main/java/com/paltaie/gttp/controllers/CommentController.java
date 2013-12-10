package com.paltaie.gttp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cd.reddit.json.mapping.RedditComment;
import com.paltaie.gttp.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/topComment")
	public ModelAndView getTopComment(@RequestParam("subreddit") String subreddit,
			@RequestParam("threadId") String threadId, @RequestParam("guess") String guess) {
		ModelAndView mav = new ModelAndView("topComment");
		RedditComment topComment = commentService.getTopComment(subreddit, threadId);
		mav.addObject("topComment", topComment);
		mav.addObject("guess", guess);
		return mav;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}
}
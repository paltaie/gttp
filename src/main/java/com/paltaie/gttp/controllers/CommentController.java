package com.paltaie.gttp.controllers;

import com.paltaie.gttp.model.RedditComment;
import com.paltaie.gttp.model.RedditLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paltaie.gttp.model.MatchResult;
import com.paltaie.gttp.service.CommentMatcherService;
import com.paltaie.gttp.service.CommentService;
import com.paltaie.gttp.service.ThreadService;

@Controller
public class CommentController {

	private CommentService commentService;
	private CommentMatcherService matcherService;
	private ThreadService threadService;

	@Autowired
	public CommentController(CommentService commentService, CommentMatcherService matcherService, ThreadService threadService) {
		this.commentService = commentService;
		this.matcherService = matcherService;
		this.threadService = threadService;
	}

	@RequestMapping(value = "/topComment.*", method = RequestMethod.POST)
	public ModelAndView getTopComment(@RequestParam("subreddit") String subreddit,
									  @RequestParam("threadId") String threadId, @RequestParam("guess") String guess) {
		ModelAndView mav = new ModelAndView("topComment");
		RedditComment topComment = commentService.getTopComment(subreddit, threadId);
		MatchResult result = matcherService.getMatchResult(topComment, guess);
		RedditLink thread = threadService.getThread(subreddit, threadId);
		mav.addObject("result", result);
		mav.addObject("thread", thread);
		return mav;
	}
}
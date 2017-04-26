package com.paltaie.gttp.service;

import com.paltaie.gttp.model.RedditComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paltaie.gttp.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;

	@Autowired
	CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Override
	public RedditComment getTopComment(String subreddit, String threadId) {
		return commentDao.getTopComment(subreddit, threadId);
	}
}

package com.paltaie.gttp.service;

import com.paltaie.gttp.model.RedditComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paltaie.gttp.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public RedditComment getTopComment(String subreddit, String threadId) {
		return commentDao.getTopComment(subreddit, threadId);
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
}

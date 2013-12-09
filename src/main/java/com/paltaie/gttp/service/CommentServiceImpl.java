package com.paltaie.gttp.service;

import javax.inject.Inject;

import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditComment;
import com.paltaie.gttp.repository.CommentDao;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao;

	@Inject
	public CommentServiceImpl(CommentDao dao) {
		this.dao = dao;
	}

	@Override
	public RedditComment getTopComment(String subreddit, String threadId) {
		try {
			return dao.getTopComment(subreddit, threadId);
		} catch (RedditException e) {
			e.printStackTrace();
		}
		return null;
	}
}

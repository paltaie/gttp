package com.paltaie.gttp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditComment;
import com.paltaie.gttp.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao dao;

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

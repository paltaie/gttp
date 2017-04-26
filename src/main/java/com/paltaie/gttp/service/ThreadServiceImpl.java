package com.paltaie.gttp.service;

import java.util.List;

import com.paltaie.gttp.model.RedditLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paltaie.gttp.repository.ThreadDao;

@Service
public class ThreadServiceImpl implements ThreadService {

	private ThreadDao threadDao;

	@Autowired
	ThreadServiceImpl(ThreadDao threadDao) {
		this.threadDao = threadDao;
	}

	@Override
	public RedditLink getThread(String subreddit, String threadId) {
		return threadDao.getThread(subreddit, threadId);
	}

	@Override
	public List<RedditLink> getListing(String subreddit, String type) {
		return threadDao.getListing(subreddit);
	}
}

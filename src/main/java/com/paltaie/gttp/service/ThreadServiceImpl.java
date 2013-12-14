package com.paltaie.gttp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.reddit.json.mapping.RedditLink;
import com.paltaie.gttp.repository.ThreadDao;

@Service
public class ThreadServiceImpl implements ThreadService {

	@Autowired
	private ThreadDao threadDao;
	
	@Override
	public RedditLink getThread(String subreddit, String threadId) {
		return threadDao.getThread(subreddit, threadId);
	}

	@Override
	public List<RedditLink> getListing(String subreddit, String type) {
		return threadDao.getListing(subreddit, type);
	}
}

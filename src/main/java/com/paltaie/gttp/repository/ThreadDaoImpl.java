package com.paltaie.gttp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cd.reddit.Reddit;
import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditLink;

@Repository
public class ThreadDaoImpl implements ThreadDao {

	@Autowired
	private Reddit reddit;
	
	@Override
	public RedditLink getThread(String subreddit, String threadId) {
		try {
			return reddit.listingFor(subreddit, threadId).get(0);
		} catch (RedditException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RedditLink> getListing(String subreddit, String type) {
		try {
			return reddit.listingFor(subreddit, type);
		} catch (RedditException e) {
			e.printStackTrace();
		}
		return null;
	}
}

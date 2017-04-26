package com.paltaie.gttp.repository;

import java.util.List;

import com.paltaie.gttp.model.RedditLink;

public interface ThreadDao {
	RedditLink getThread(final String subreddit, final String threadId);
	List<RedditLink> getListing(final String subreddit);
}

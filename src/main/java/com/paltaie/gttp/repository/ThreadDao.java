package com.paltaie.gttp.repository;

import java.util.List;

import com.cd.reddit.json.mapping.RedditLink;

public interface ThreadDao {
	RedditLink getThread(final String subreddit, final String threadId);
	List<RedditLink> getListing(final String subreddit, final String type);
}

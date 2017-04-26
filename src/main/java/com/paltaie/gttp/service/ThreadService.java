package com.paltaie.gttp.service;

import com.paltaie.gttp.model.RedditLink;

import java.util.List;

public interface ThreadService {
	RedditLink getThread(final String subreddit, final String threadId);
	List<RedditLink> getListing(final String subreddit, final String type);
}

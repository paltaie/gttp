package com.paltaie.gttp.service;

import java.util.List;

import com.cd.reddit.json.mapping.RedditLink;

public interface ThreadService {
	RedditLink getThread(final String subreddit, final String threadId);
	List<RedditLink> getListing(final String subreddit, final String type);
}

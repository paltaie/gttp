package com.paltaie.gttp.service;

import com.cd.reddit.json.mapping.RedditComment;

public interface CommentService {
	RedditComment getTopComment(String subreddit, String threadId);
}

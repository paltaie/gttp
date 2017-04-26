package com.paltaie.gttp.service;

import com.paltaie.gttp.model.RedditComment;

public interface CommentService {
	RedditComment getTopComment(String subreddit, String threadId);
}

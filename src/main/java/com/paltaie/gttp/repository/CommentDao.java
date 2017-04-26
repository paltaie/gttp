package com.paltaie.gttp.repository;

import com.paltaie.gttp.model.RedditComment;

public interface CommentDao {
	RedditComment getTopComment(String subreddit, String threadId);
}

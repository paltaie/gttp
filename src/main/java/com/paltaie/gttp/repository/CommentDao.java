package com.paltaie.gttp.repository;

import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditComment;

public interface CommentDao {
	RedditComment getTopComment(String subreddit, String threadId) throws RedditException;
}

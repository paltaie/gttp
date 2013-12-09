package com.paltaie.gttp.repository;

import java.util.List;

import com.cd.reddit.Reddit;
import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditComment;
import com.cd.reddit.json.util.RedditComments;

public class CommentDaoImpl implements CommentDao {
	public RedditComment getTopComment(String subreddit, String threadId) throws RedditException {
		RedditComment topComment = null;
		Reddit reddit = new Reddit("GTTP");
		reddit.login("why_not_cats", "matthew");
		RedditComments comments = reddit.commentsFor("cringepics", "1sgoo0");
		List<RedditComment> commentList = comments.getComments();
		for (RedditComment comment : commentList) {
			if (topComment == null || comment.getUps() > topComment.getUps()) {
				topComment = comment;
			}
		}
		return topComment;
	}
}

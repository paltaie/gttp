package com.paltaie.gttp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cd.reddit.Reddit;
import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditComment;
import com.cd.reddit.json.util.RedditComments;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	private Reddit reddit;
	
	public RedditComment getTopComment(String subreddit, String threadId) throws RedditException {
		RedditComment topComment = null;
		RedditComments comments = reddit.commentsFor(subreddit, threadId);
		List<RedditComment> commentList = comments.getComments();
		for (RedditComment comment : commentList) {
			if (topComment == null || comment.getUps() > topComment.getUps()) {
				topComment = comment;
			}
		}
		return topComment;
	}
}

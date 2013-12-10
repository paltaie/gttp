package com.paltaie.gttp.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cd.reddit.Reddit;
import com.cd.reddit.RedditException;
import com.cd.reddit.json.mapping.RedditComment;
import com.cd.reddit.json.util.RedditComments;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	public RedditComment getTopComment(String subreddit, String threadId) throws RedditException {
		RedditComment topComment = null;
		Reddit reddit = new Reddit("GtTP/0.0.1-SNAPSHOT");
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

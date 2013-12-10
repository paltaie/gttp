package com.paltaie.gttp.service;

import com.cd.reddit.json.mapping.RedditComment;
import com.paltaie.gttp.model.MatchResult;

public interface CommentMatcherService {
	MatchResult getMatchResult(RedditComment topComment, String guess);
}

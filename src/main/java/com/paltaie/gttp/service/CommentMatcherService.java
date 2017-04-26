package com.paltaie.gttp.service;

import com.paltaie.gttp.model.MatchResult;
import com.paltaie.gttp.model.RedditComment;

public interface CommentMatcherService {
	MatchResult getMatchResult(RedditComment topComment, String guess);
}

package com.paltaie.gttp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cd.reddit.json.mapping.RedditComment;
import com.paltaie.gttp.model.MatchResult;

@Service
public class CommentMatcherServiceImpl implements CommentMatcherService {
	@Override
	public MatchResult getMatchResult(RedditComment topComment, String guess) {
		MatchResult result = new MatchResult(topComment, guess);
		List<String> matchedWords = new ArrayList<String>();
		String topCommentUpper = topComment.getBody()
				.toUpperCase();
		String guessUpper = guess.toUpperCase()
				.replaceAll(",", " ")
				.replaceAll("\\.", " ")
				.toUpperCase();
		for (String token : guessUpper.split(" ")) {
			String trimmedToken = token.trim();
			if (topCommentUpper.contains(trimmedToken)) {
				matchedWords.add(trimmedToken);
			}
		}
		result.setMatchedWords(matchedWords);
		return result;
	}
}

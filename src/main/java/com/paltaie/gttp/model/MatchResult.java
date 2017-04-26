package com.paltaie.gttp.model;

import lombok.Data;

import java.util.List;

@Data
public class MatchResult {
	private RedditComment topComment;
	private String guess;
	private List<String> matchedWords;
	
	public MatchResult(RedditComment topComment, String guess) {
		this.topComment = topComment;
		this.guess = guess;
	}
}

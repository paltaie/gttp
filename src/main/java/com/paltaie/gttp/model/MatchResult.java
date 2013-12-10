package com.paltaie.gttp.model;

import java.util.List;

import com.cd.reddit.json.mapping.RedditComment;

public class MatchResult {
	private RedditComment topComment;
	private String guess;
	private List<String> matchedWords;
	
	public MatchResult(RedditComment topComment, String guess) {
		this.topComment = topComment;
		this.guess = guess;
	}
	
	public RedditComment getTopComment() {
		return topComment;
	}
	public void setTopComment(RedditComment topComment) {
		this.topComment = topComment;
	}
	public String getGuess() {
		return guess;
	}
	public void setGuess(String guess) {
		this.guess = guess;
	}
	public List<String> getMatchedWords() {
		return matchedWords;
	}

	public void setMatchedWords(List<String> matchedWords) {
		this.matchedWords = matchedWords;
	}
}

package com.paltaie.gttp.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class MatchResult {
	@NonNull
	private RedditComment topComment;
	@NonNull
	private String guess;
	private List<String> matchedWords;
}

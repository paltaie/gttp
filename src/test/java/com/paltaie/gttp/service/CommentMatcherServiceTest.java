package com.paltaie.gttp.service;

import com.paltaie.gttp.model.MatchResult;
import com.paltaie.gttp.model.RedditComment;
import org.junit.Assert;
import org.junit.Test;

public class CommentMatcherServiceTest {

	private CommentMatcherService underTest = new CommentMatcherServiceImpl();

	@Test
	public void testMatch() {
		RedditComment comment = new RedditComment();
		comment.setBody("I am a test");
		MatchResult result = underTest.getMatchResult(comment, "test");
		Assert.assertEquals("I am a test", result.getTopComment().getBody());
		Assert.assertEquals("test", result.getGuess());
		Assert.assertEquals(1, result.getMatchedWords().size());
	}
}

package com.paltaie.gttp.service;

import com.paltaie.gttp.config.GttpConfig;
import com.paltaie.gttp.model.MatchResult;
import com.paltaie.gttp.model.RedditComment;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

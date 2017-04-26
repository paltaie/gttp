package com.paltaie.gttp.service;

import com.paltaie.gttp.model.MatchResult;
import com.paltaie.gttp.model.RedditComment;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-context.xml")
public class CommentMatcherServiceTest {
	@Autowired
	private CommentMatcherService service;
	
	@Test
	@Ignore
	public void testMatch() {
		RedditComment comment = new RedditComment();
		comment.setBody("I am a test");
		MatchResult result = service.getMatchResult(comment, "test");
		Assert.assertEquals("I am a test", result.getTopComment().getBody());
		Assert.assertEquals("test", result.getGuess());
		Assert.assertEquals(1, result.getMatchedWords().size());
	}
}

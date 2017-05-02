package com.paltaie.gttp.controllers;

import com.paltaie.gttp.model.MatchResult;
import com.paltaie.gttp.model.RedditComment;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.service.CommentMatcherService;
import com.paltaie.gttp.service.CommentService;
import com.paltaie.gttp.service.ThreadService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeAvailable;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class CommentControllerTest {

    private CommentController underTest;
    private CommentService commentService = mock(CommentService.class);
    private CommentMatcherService commentMatcherService = mock(CommentMatcherService.class);
    private ThreadService threadService = mock(ThreadService.class);

    private MatchResult matchResult;

    @Before
    public void setUp() throws Exception {

        RedditComment redditComment = new RedditComment();
        redditComment.setBody("I like guess cats");

        matchResult = new MatchResult(redditComment, "guess");
        matchResult.setMatchedWords(Collections.singletonList("guess"));

        when(commentService.getTopComment("234", "214")).thenReturn(redditComment);
        when(commentMatcherService.getMatchResult(any(RedditComment.class), eq("guess"))).thenReturn(matchResult);
        when(threadService.getThread("234", "214")).thenReturn(new RedditLink());

        underTest = new CommentController(commentService, commentMatcherService, threadService);
    }

    @Test
    public void getTopComment() throws Exception {
        ModelAndView mav = underTest.getTopComment("234", "214", "guess");
        verify(commentService).getTopComment("234", "214");
        verify(commentMatcherService).getMatchResult(any(RedditComment.class), eq("guess"));
        verify(threadService).getThread("234", "214");
        assertViewName(mav, "topComment");
        assertModelAttributeAvailable(mav, "result");
        assertModelAttributeAvailable(mav, "thread");
        assertModelAttributeValue(mav, "result", matchResult);
    }
}
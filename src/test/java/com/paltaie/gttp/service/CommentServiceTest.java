package com.paltaie.gttp.service;

import com.paltaie.gttp.model.RedditComment;
import com.paltaie.gttp.repository.CommentDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentServiceTest {

    private CommentService underTest;
    private RedditComment redditComment;

    @Before
    public void setUp() {
        CommentDao commentDao = mock(CommentDao.class);
        underTest = new CommentServiceImpl(commentDao);

        redditComment = new RedditComment();
        redditComment.setAuthor("ME");
        redditComment.setBody("BODY");
        redditComment.setDowns(32);
        redditComment.setUps(42);

        when(commentDao.getTopComment(anyString(), anyString())).thenReturn(redditComment);
    }

    @Test
    public void testGetTopComment() throws Exception {
        RedditComment response = underTest.getTopComment("mytest", "213123");
        assertEquals(redditComment, response);
    }
}
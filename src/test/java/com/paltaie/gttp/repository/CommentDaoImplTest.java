package com.paltaie.gttp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paltaie.gttp.model.RedditComment;
import com.paltaie.gttp.utils.RedditClient;
import org.junit.Before;
import org.junit.Test;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentDaoImplTest {

    private CommentDao commentDao;

    @Before
    public void setUp() throws Exception {
        String sampleJson = new String(readAllBytes(get(getClass().getResource("/thread-response-sample.json").toURI())));
        RedditClient redditClient = mock(RedditClient.class);
        when(redditClient.makeRequest(anyString(), any())).thenReturn("ALSDJASDLASD");
        when(redditClient.makeRequest("https://www.reddit.com/r/{subreddit}/comments/{threadId}.json", "test", "test")).thenReturn(sampleJson);
        commentDao = new CommentDaoImpl(redditClient, new ObjectMapper());
    }

    @Test
    public void getTopComment() throws Exception {
        RedditComment redditComment = commentDao.getTopComment("test", "test");
        assertEquals(16097, redditComment.getUps());
        assertEquals(0, redditComment.getDowns());
        assertEquals("leo_99", redditComment.getAuthor());
    }

    @Test(expected = IllegalStateException.class)
    public void getTopCommentBad() throws Exception {
        RedditComment redditComment = commentDao.getTopComment("asdfsdf", "asdfasdf");
    }
}
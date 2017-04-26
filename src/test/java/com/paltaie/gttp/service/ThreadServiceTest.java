package com.paltaie.gttp.service;

import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.repository.ThreadDao;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThreadServiceTest {

    private ThreadService underTest;
    private RedditLink redditLink;

    @Before
    public void setUp() throws Exception {
        ThreadDao threadDao = mock(ThreadDao.class);
        underTest = new ThreadServiceImpl(threadDao);
        redditLink = new RedditLink();
        redditLink.setAuthor("AUTHOR");
        redditLink.setDowns(422);
        redditLink.setUps(349023);
        redditLink.setTitle("title");
        redditLink.setId("34532453");
        redditLink.setUrl("http://example.org");
        when(threadDao.getThread(anyString(), anyString())).thenReturn(redditLink);
        when(threadDao.getListing(anyString())).thenReturn(Collections.singletonList(redditLink));
    }

    @Test
    public void testGetThread() throws Exception {
        RedditLink response = underTest.getThread("alsdfkj", "asdklj");
        assertEquals(redditLink, response);
    }

    @Test
    public void testGetListing() throws Exception {
        List<RedditLink> response = underTest.getListing("alsdfkj", "Hot");
        assertEquals(1, response.size());
        assertEquals(redditLink, response.get(0));
    }

}
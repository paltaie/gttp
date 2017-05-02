package com.paltaie.gttp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.utils.RedditClient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ThreadDaoImplTest {

    private ThreadDao threadDao;

    @Before
    public void setUp() throws Exception {
        String sampleJsonHot = new String(readAllBytes(get(getClass().getResource("/subreddit-hot-sample.json").toURI())));
        String sampleJsonThread = new String(readAllBytes(get(getClass().getResource("/thread-response-sample.json").toURI())));
        RedditClient redditClient = mock(RedditClient.class);
        when(redditClient.makeRequest(anyString(), any())).thenReturn(sampleJsonThread);
        when(redditClient.makeRequest("https://www.reddit.com/r/{subreddit}/hot.json", "SUBREDDIT")).thenReturn(sampleJsonHot);
        threadDao = new ThreadDaoImpl(redditClient, new ObjectMapper());
    }

    @Test
    public void getListing() throws Exception {
        List<RedditLink> redditLinks = threadDao.getListing("SUBREDDIT");
        assertEquals(26, redditLinks.size());
    }

    @Test
    public void testGetThread() {
        RedditLink redditLink = threadDao.getThread("asdf", "asdf");
        assertEquals("This is what a nation rejecting dictatorship looks like.", redditLink.getTitle());
        assertEquals("leo_99", redditLink.getAuthor());
        assertEquals(115571, redditLink.getUps());
    }
}
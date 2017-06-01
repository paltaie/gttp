package com.paltaie.gttp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.service.ThreadService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.ModelAndViewAssert.assertModelAttributeValue;
import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

public class ListingControllerTest {

    private ListingController listingController;
    private ThreadService threadService = mock(ThreadService.class);

    @Before
    public void setUp() throws Exception {
        when(threadService.getListing("SUBREDDIT", "hot")).thenReturn(Arrays.asList(new RedditLink(), new RedditLink()));
        listingController = new ListingController(threadService);
    }

    @Test
    public void getSubreddit() throws Exception {
        List<RedditLink> redditLinks = listingController.getSubreddit("SUBREDDIT");
        assertEquals(2, redditLinks.size());
    }

}
package com.paltaie.gttp.utils;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

public class RedditClientTest {

    private RestTemplate restTemplate = new RestTemplate();
    private RedditClient underTest = new RedditClient(restTemplate);
    private MockRestServiceServer server;

    @Before
    public void setUp() throws Exception {
        server = MockRestServiceServer
                .bindTo(restTemplate)
                .build();
    }

    @Test
    public void testMakeRequest() {
        server.expect(once(), requestTo("/i/am/a/test.png"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"I\": \"LIKE CATS\"}", MediaType.APPLICATION_JSON));
        underTest.makeRequest("/i/am/{thing}/{thing2}.png", "a", "test");
        server.verify();
    }
}
package com.paltaie.gttp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RedditClient {

    private static final String USER_AGENT = "gttp/1.0";

    private RestTemplate restTemplate;

    @Autowired
    public RedditClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String makeRequest(String url, String... variables) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.USER_AGENT, USER_AGENT);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class,
                variables);
        return responseEntity.getBody();
    }
}

package com.paltaie.gttp.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.model.RedditLinkWrapper;
import com.paltaie.gttp.utils.RedditClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

@Repository
public class ThreadDaoImpl implements ThreadDao {

	private RedditClient redditClient;
	private ObjectMapper objectMapper;

	@Autowired
	public ThreadDaoImpl(RedditClient redditClient, ObjectMapper objectMapper) {
		this.redditClient = redditClient;
		this.objectMapper = objectMapper;
	}

	@Override
	public RedditLink getThread(String subreddit, String threadId) {
		String response = redditClient.makeRequest(
				"https://www.reddit.com/r/{subreddit}/comments/{threadId}.json",
				subreddit,
				threadId);

		try {
			ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(response);
			ArrayNode arrayNode1 = (ArrayNode) arrayNode.get(0).get("data").get("children");
			JsonNode jsonNode = arrayNode1.get(0);
			return objectMapper.treeToValue(jsonNode, RedditLinkWrapper.class).getData();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RedditLink> getListing(String subreddit) {
		String response = redditClient.makeRequest(
				"https://www.reddit.com/r/{subreddit}/hot.json",
				subreddit);
		try {
			ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(response).get("data").get("children");
			List<RedditLinkWrapper> redditLinkWrappers = Arrays.asList(objectMapper.treeToValue(arrayNode, RedditLinkWrapper[].class));
			List<RedditLink> redditLinks = new ArrayList<>();
			redditLinkWrappers.forEach(redditLinkWrapper -> redditLinks.add(redditLinkWrapper.getData()));
			return redditLinks;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

package com.paltaie.gttp.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.paltaie.gttp.model.RedditLink;
import com.paltaie.gttp.model.RedditLinkWrapper;
import com.paltaie.gttp.utils.RedditClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Repository
public class ThreadDaoImpl implements ThreadDao {
	private static final Logger LOG = LoggerFactory.getLogger(ThreadDaoImpl.class);

	private RedditClient redditClient;
	private ObjectMapper objectMapper;

	@Autowired
	ThreadDaoImpl(RedditClient redditClient, ObjectMapper objectMapper) {
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
			JsonNode jsonNode = objectMapper
					.readTree(response)
					.get(0)
					.get("data")
					.get("children")
					.get(0);
			return objectMapper.treeToValue(jsonNode, RedditLinkWrapper.class).getData();
		} catch (IOException e) {
			LOG.error("Encountered an error while reading response from reddit", e);
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
			return Arrays
					.stream(objectMapper.treeToValue(arrayNode, RedditLinkWrapper[].class))
					.map(RedditLinkWrapper::getData)
					.collect(toList());
		} catch (IOException e) {
			LOG.error("Encountered an error while reading response from reddit", e);
		}
		return null;
	}
}

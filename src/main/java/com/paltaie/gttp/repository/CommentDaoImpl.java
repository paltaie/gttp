package com.paltaie.gttp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.paltaie.gttp.model.RedditComment;
import com.paltaie.gttp.model.RedditCommentWrapper;
import com.paltaie.gttp.utils.RedditClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;

@Repository
public class CommentDaoImpl implements CommentDao {

    private static final Logger LOG = LoggerFactory.getLogger(CommentDaoImpl.class);
    private RedditClient redditClient;
	private ObjectMapper objectMapper;

	@Autowired
    CommentDaoImpl(RedditClient redditClient, ObjectMapper objectMapper) {
        this.redditClient = redditClient;
        this.objectMapper = objectMapper;
    }

    public RedditComment getTopComment(String subreddit, String threadId) {
        String response = redditClient.makeRequest(
                "https://www.reddit.com/r/{subreddit}/comments/{threadId}.json",
                subreddit,
                threadId
        );
        List<RedditCommentWrapper> redditCommentWrappers = new ArrayList<>();
        try {
            ArrayNode arrayNode = (ArrayNode) objectMapper
                    .readTree(response)
                    .get(1)
                    .get("data")
                    .get("children");
            redditCommentWrappers = asList(objectMapper.treeToValue(arrayNode, RedditCommentWrapper[].class));
        } catch (IOException e) {
            LOG.error("Encountered an error while reading response from reddit", e);
        }
        return redditCommentWrappers
                .stream()
                .map(RedditCommentWrapper::getData)
                .max(comparingInt(RedditComment::getUps))
                .orElseThrow(() -> new IllegalStateException("Failed to find the highest-rated subreddit comment"));
    }
}

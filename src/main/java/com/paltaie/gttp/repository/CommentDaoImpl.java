package com.paltaie.gttp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.paltaie.gttp.model.RedditComment;
import com.paltaie.gttp.model.RedditCommentWrapper;
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
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparingInt;

@Repository
public class CommentDaoImpl implements CommentDao {

    private RedditClient redditClient;
	private ObjectMapper objectMapper;

	@Autowired
    public CommentDaoImpl(RedditClient redditClient, ObjectMapper objectMapper) {
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
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(response);
            ArrayNode myNode = (ArrayNode) arrayNode.get(1).get("data").get("children");
            redditCommentWrappers = asList(objectMapper.treeToValue(myNode, RedditCommentWrapper[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<RedditComment> redditComments = new ArrayList<>();
        redditCommentWrappers.forEach(redditCommentWrapper -> redditComments.add(redditCommentWrapper.getData()));

        return redditComments.stream().max(comparingInt(RedditComment::getUps))
            .get();
    }
}

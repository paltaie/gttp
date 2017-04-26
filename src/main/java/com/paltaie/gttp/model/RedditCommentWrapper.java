package com.paltaie.gttp.model;

import lombok.Data;

@Data
public class RedditCommentWrapper {
    private String kind;
    private RedditComment data;
}

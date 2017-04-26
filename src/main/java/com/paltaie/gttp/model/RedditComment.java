package com.paltaie.gttp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditComment {
    private int ups;
    private int downs;
    private String body;
    private String author;
}

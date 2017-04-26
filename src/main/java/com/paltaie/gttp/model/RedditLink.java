package com.paltaie.gttp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditLink {
    private String id;
    private String title;
    private int ups;
    private int downs;
    private String url;
    private String author;
}

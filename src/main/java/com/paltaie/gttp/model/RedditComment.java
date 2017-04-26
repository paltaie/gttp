package com.paltaie.gttp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditComment {
    private int ups;
    private int downs;
    private String body;
    private String author;
}

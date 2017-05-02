package com.paltaie.gttp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedditLinkWrapper {
    private RedditLink data;
}

package com.example.urlshortener.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OriginalUrlRequestView {

    @JsonProperty("originalUrl")
    private String originalUrl;

//    @JsonProperty("customUrl")
//    private String customUrl;
}

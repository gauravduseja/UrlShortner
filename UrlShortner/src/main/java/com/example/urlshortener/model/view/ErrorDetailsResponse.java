package com.example.urlshortener.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDetailsResponse {

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "description")
    private String description;

}

package com.example.urlshortener.model.view;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class GenericResponse {

    @JsonProperty(value = "success")
    private boolean success;

    @JsonProperty(value = "error")
    private ErrorDetailsResponse errorDetailsResponse;

}

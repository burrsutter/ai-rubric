package com.burrsutter;

import com.fasterxml.jackson.annotation.JsonCreator;

public record TestResponse(String request, String response, float score) {
     @JsonCreator
    public TestResponse {

    }   
}

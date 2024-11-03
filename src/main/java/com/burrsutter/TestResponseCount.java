package com.burrsutter;

import com.fasterxml.jackson.annotation.JsonCreator;

public record TestResponseCount(String request, String response, int count) {
     @JsonCreator
    public TestResponseCount {

    }   
}

package com.burrsutter;

import com.fasterxml.jackson.annotation.JsonCreator;

public record TestTwoStringsResponse(String input1, String input2, String output, float score) {
    @JsonCreator
    public TestTwoStringsResponse {

    }
}

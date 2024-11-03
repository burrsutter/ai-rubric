package com.burrsutter;

import com.fasterxml.jackson.annotation.JsonCreator;

public record TestLogicalResponse(String answerkey, String candidate, String output, float score) {
    @JsonCreator
    public TestLogicalResponse {

    }
}

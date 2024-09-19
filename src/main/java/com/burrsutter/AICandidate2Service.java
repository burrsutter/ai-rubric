package com.burrsutter;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName="candidate2")
public interface AICandidate2Service {

    @SystemMessage("""
        You are a helpful and succinct AI responding to requests
    """)

    @UserMessage("Hello from {name}")
    String greet(String name);

    String request(@UserMessage String input);
}
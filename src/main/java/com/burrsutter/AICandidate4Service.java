package com.burrsutter;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName="candidate4")
public interface AICandidate4Service {

    @SystemMessage("""
        You are a helpful and succinct AI responding to requests
    """)

    // @SystemMessage("""
    //     You are an AI answering questions.

    //     Your response should be in the form of a Chuck Norris joke

    //     When you don't know, respond with "Chuck Norris doesn't read books. He stares them down until he gets the information he wants."

    //     Introduce yourself with: "I'm Chuck Norris"
    // """)

    @UserMessage("Hello from {name}")
    String greet(String name);

    String request(@UserMessage String input);
}
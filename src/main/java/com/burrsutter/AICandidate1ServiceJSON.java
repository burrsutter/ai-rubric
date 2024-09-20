package com.burrsutter;

import java.time.temporal.ChronoUnit;
import org.eclipse.microprofile.faulttolerance.Timeout;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName="candidate1")
public interface AICandidate1ServiceJSON {

    @SystemMessage("""
        You are a helpful and succinct AI responding to requests        
    """)

    @UserMessage("""
        Your task is execute the {request}

        Respond with a **single** JSON document containing:    
        - the 'request' key set to {request}
        - the 'response' key set to your response
        - the 'score' is a random number between 0 and 1.0

        Your response must be just the raw JSON document, without ```json, ``` or anything else.

    """)
    @Timeout(value = 2, unit = ChronoUnit.MINUTES)
    TestResponse request(String request);

}
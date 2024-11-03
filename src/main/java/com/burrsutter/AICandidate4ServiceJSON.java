package com.burrsutter;

import java.time.temporal.ChronoUnit;
import org.eclipse.microprofile.faulttolerance.Timeout;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName="candidate4", 
    chatMemoryProviderSupplier = RegisterAiService.NoChatMemoryProviderSupplier.class)
public interface AICandidate4ServiceJSON {

    @SystemMessage("""
        You are a helpful and succinct AI responding to requests        
    """)

    @UserMessage("""
        Your task is execute the request delimited by ---,

        Respond with a **single** JSON document containing:    
        - the 'request' key set to {request}
        - the 'response' key set to your response
        - the 'count' key is number you have calculated 

        Your response must be just the raw JSON document, without ```json, ``` or anything else.

        ----
        {request}
        ----

    """)
    @Timeout(value = 2, unit = ChronoUnit.MINUTES)
    TestResponseCount request(String request);

}
package com.burrsutter;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService()
public interface AITestTwoStringsService {
    @SystemMessage("""
        Please test two strings and return a score using cosign similarity

        You will always answer with a JSON document, and only this JSON document.
    """)

    @UserMessage("""
        Your task is to provide a cosign similarity score between two strings delimited by ---

        Your answer needs to contain the following information and be formatted as JSON:
        - the 'input1' key set to {input1}
        - the 'input2' key set to {input2}
        - the 'output' key set to your response
        - the 'score' cosign simiarity score you calculate

        ---
        {input1}
        ---

        ---
        {input2}
        ---
    """)

    TestTwoStringsResponse test(String input1, String input2);
} 

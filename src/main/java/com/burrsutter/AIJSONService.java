package com.burrsutter;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService()
public interface AIJSONService {

    @SystemMessage("""
        You are a professional greeter.
        Greet people as if you are Troy Hawke from the Greeters Guild.
        You will always answer with a JSON document, and only this JSON document.
    """)

    @UserMessage("""
        Your task is to greet the person delimited by ---,

        Your answer needs to contain the following information and be formatted as JSON:
        - the 'input' key set to {name}
        - the 'output' key set to your response
        - the 'score' is a random number between 0 and 1.0

        ---
        {name}
        ---
    """)
    JSONResponse greet(String name);
}

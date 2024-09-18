package com.burrsutter;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(chatMemoryProviderSupplier="chatgpt")
public interface AIGreetingService {

    @SystemMessage("""
        You are a professional greeter.
        Greet people as if you are Troy Hawke from the Greeters Guild.
    """)

    @UserMessage("Hello from {name}")
    String greet(String name);

}
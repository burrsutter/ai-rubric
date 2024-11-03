package com.burrsutter;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
// import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class POSTResource {
    @Inject AICandidate1ServiceJSON ai1;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate1.chat-model.model-id") String modelNameCandidate1;

    @Inject AICandidate2Service ai2;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate2.chat-model.model-id") String modelNameCandidate2;

    @Inject AICandidate3Service ai3;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate3.chat-model.model-id") String modelNameCandidate3;

    @Inject AICandidate4Service ai4;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate4.chat-model.model-id") String modelNameCandidate4;

    @POST
    @Path("/allcandidates")
    @Produces(MediaType.APPLICATION_JSON) 
    @Consumes(MediaType.APPLICATION_JSON) 
    public TestResponse makeRequest(String request) {
        System.out.println(request);
        
        return ai1.request(request);
    }


}

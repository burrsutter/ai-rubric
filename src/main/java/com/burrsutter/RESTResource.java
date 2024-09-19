package com.burrsutter;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.annotations.Property;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
public class RESTResource {

    @Inject AIGreetingService aigreeter;

    @Inject AIJSONService aijsongreeter;

    @Inject AITestTwoStringsService aicosigntester;

    @Inject AIJudgeService aijudge;

    @ConfigProperty(name="quarkus.langchain4j.openai.judge.chat-model.model-name") String modelJudge;

    @Inject AICandidate1Service aicandidate1;

    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate1.chat-model.model-id") String modelNameCandidate1;

    @Inject AICandidate2Service aicandidate2;

    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate2.chat-model.model-id") String modelNameCandidate2;

    @Inject AICandidate3Service aicandidate3;

    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate3.chat-model.model-id") String modelNameCandidate3;

    
    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("/helloai")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloai() {
        return aigreeter.greet("Burr");
    }

    @GET
    @Path("/helloaijson")
    @Produces(MediaType.APPLICATION_JSON)
    public String helloaijson() {
        ObjectMapper mapper = new ObjectMapper();
        
        JSONResponse response = aijsongreeter.greet("Burr");

        String json = null;
        
        try {
            json = mapper.writeValueAsString(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + json + "\n");

        System.out.println("score: " + response.score());

        // String stringresult = response.toString();
        
        return json;
    }

    @GET
    @Path("/cosigntest")
    @Produces(MediaType.APPLICATION_JSON)
    public String consigntest() {
        
        ObjectMapper mapper = new ObjectMapper();

        // Who is Burr Sutter in 25 words or less?

        String input1 = "Burr Sutter is a software engineer and consultant, specializing in Angular. (Twitter: @burrsutter)"; // mistral-nemo:12b
        String input2 = "Burr Sutter was a Canadian football player, coach, and sports broadcaster; also known for his on-air antics as a\n" + //
                        "colour commentator."; // llama3.1

        TestTwoStringsResponse response = aicosigntester.test(input1, input2);

        System.out.println(response.output());

        System.out.println(response.score());

        if (response.score() <= .6) {
            System.out.println("Score is LOW");
        }

        String json = null;
        
        try {
            json = mapper.writeValueAsString(response);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return json;

    }

    // Candidate1
    @GET
    @Path("/hellocandidate1")
    @Produces(MediaType.TEXT_PLAIN)
    public String hellocandidate1() {
        return aicandidate1.greet("Burr");
    }
    @GET
    @Path("/requestcandidate1")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestcandidate1() {
        return aicandidate1.request("why is the sky blue in 25 words or less?");
    }

    // Candidate2
    @GET
    @Path("/hellocandidate2")
    @Produces(MediaType.TEXT_PLAIN)
    public String hellocandidate2() {
        return aicandidate2.greet("Burr");
    }
    @GET
    @Path("/requestcandidate2")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestcandidate2() {
        return aicandidate2.request("why is the sky blue in 25 words or less?");
    }

    // Candidate3
    @GET
    @Path("/hellocandidate3")
    @Produces(MediaType.TEXT_PLAIN)
    public String hellocandidate3() {
        return aicandidate3.greet("Burr");
    }
    @GET
    @Path("/requestcandidate3")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestcandidate3() {
        return aicandidate3.request("why is the sky blue in 25 words or less?");
    }

    // Compare 1 to 2
    // why is the sky blue
    @GET
    @Path("/compare1to2Sky")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to2Sky() {
        String candidate1Response = aicandidate1.request("why is the sky blue in 25 words or less?");
        String candidate2Response = aicandidate2.request("why is the sky blue in 25 words or less?");

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate2Response);

        System.out.println(response.output());
        System.out.println(modelNameCandidate1 + ": " + candidate1Response + "\n");
        System.out.println(modelNameCandidate2 + ":" + candidate2Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }

    // Compare 1 to 3
    // why is the sky blue
    @GET
    @Path("/compare1to3Sky")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to3Sky() {
        String candidate1Response = aicandidate1.request("why is the sky blue in 25 words or less?");
        String candidate3Response = aicandidate3.request("why is the sky blue in 25 words or less?");

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate3Response);

        System.out.println(response.output());
        System.out.println(modelNameCandidate1 + ": " + candidate1Response + "\n");
        System.out.println(modelNameCandidate3 + ": " + candidate3Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }

    // Compare 1 to 2
    // who is Burr Sutter
    @GET
    @Path("/compare1to2Burr")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to2Burr() {
        String candidate1Response = aicandidate1.request("who is Burr Sutter in 25 words or less?");
        String candidate2Response = aicandidate2.request("who is Burr Sutter in 25 words or less?");

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate2Response);

        System.out.println(response.output());
        System.out.println(modelNameCandidate1 + ": " + candidate1Response + "\n");
        System.out.println(modelNameCandidate2 + ": " + candidate2Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }

    // Compare 1 to 2
    // who is Burr Sutter
    @GET
    @Path("/compare1to3Burr")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to3Burr() {
        String candidate1Response = aicandidate1.request("who is Burr Sutter in 25 words or less?");
        String candidate3Response = aicandidate3.request("who is Burr Sutter in 25 words or less?");

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate3Response);

        System.out.println(response.output());
        System.out.println("Candidate 1:" + candidate1Response + "\n");
        System.out.println("Candidate 3:" + candidate3Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }    

    // Compare candidates to judge
    // who is Burr Sutter
    @GET
    @Path("/comparetoJudge")
    @Produces(MediaType.TEXT_PLAIN)
    public String comparetoJudge() {
        String judgeResponse = aijudge.request("who is Burr Sutter in 25 words or less?");

        // Candidate 1 - mistral-nemo:12b
        String candidate1Response = aicandidate1.request("who is Burr Sutter in 25 words or less?");
        TestTwoStringsResponse test1response = aicosigntester.test(judgeResponse, candidate1Response);
        

        // Candidate 2 - llama3.1:8b
        String candidate2Response = aicandidate2.request("who is Burr Sutter in 25 words or less?");
        TestTwoStringsResponse test2response = aicosigntester.test(judgeResponse, candidate2Response);
        
        // Candidate 3 - qwen2.5:7b
        String candidate3Response = aicandidate3.request("who is Burr Sutter in 25 words or less?");
        TestTwoStringsResponse test3response = aicosigntester.test(judgeResponse, candidate3Response);

        String judgePrompt = "which of the following numbers is the highest score: " +
            test1response.score() + " " +
            test2response.score() + " " +
            test3response.score();

        String judgedWinner = aijudge.request(judgePrompt);

        System.out.println("\nCandiate 1 (mistral-nemo:12b) vs Judge: ");
        System.out.println(test1response.output());
        System.out.println("\nJudge:" + judgeResponse + "\n");
        System.out.println("Candidate 1:" + candidate1Response + "\n");
        System.out.println(test1response.score());

        System.out.println("\nCandiate 2 (llama3.1:8b) vs Judge: ");
        System.out.println(test2response.output());
        System.out.println("\nJudge:" + judgeResponse + "\n");
        System.out.println("Candidate 2:" + candidate1Response + "\n");
        System.out.println(test2response.score());

        System.out.println("\nCandiate 3 (qwen2.5:7b) vs Judge: ");
        System.out.println(test3response.output());
        System.out.println("\nJudge:" + judgeResponse + "\n");
        System.out.println("Candidate 3:" + candidate1Response + "\n");
        System.out.println(test3response.score());


        System.out.println("\n\n" + judgePrompt);
        System.out.println(judgedWinner);


        return "Winner is " + judgedWinner;
        
    }

    


}

package com.burrsutter;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;


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

    @Inject AICandidate4Service aicandidate4;

    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate4.chat-model.model-id") String modelNameCandidate4;

    private static final String WHY_IS_THE_SKY_BLUE = "why is the sky blue in 25 words or less?";

    private static final String WHO_IS_BURR_SUTTER = "who is Burr Sutter in 25 words or less?";
    

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
        return aicandidate1.request(WHY_IS_THE_SKY_BLUE);
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
        return aicandidate2.request(WHY_IS_THE_SKY_BLUE);
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
        return aicandidate3.request(WHY_IS_THE_SKY_BLUE);
    }

    // Candidate4
    @GET
    @Path("/hellocandidate4")
    @Produces(MediaType.TEXT_PLAIN)
    public String hellocandidate4() {
        return modelNameCandidate4 + ": " + aicandidate4.greet("Burr");
    }
    @GET
    @Path("/requestcandidate4")
    @Produces(MediaType.TEXT_PLAIN)
    public String requestcandidate4() {
        return modelNameCandidate4 + ": " + aicandidate4.request(WHY_IS_THE_SKY_BLUE);
    }

    // Compare 1 to 2
    // why is the sky blue
    @GET
    @Path("/compare1to2Sky")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to2Sky() {
        String candidate1Response = aicandidate1.request(WHY_IS_THE_SKY_BLUE);
        String candidate2Response = aicandidate2.request(WHY_IS_THE_SKY_BLUE);

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
        String candidate1Response = aicandidate1.request(WHY_IS_THE_SKY_BLUE);
        String candidate3Response = aicandidate3.request(WHY_IS_THE_SKY_BLUE);

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate3Response);

        System.out.println(response.output());
        System.out.println(modelNameCandidate1 + ": " + candidate1Response + "\n");
        System.out.println(modelNameCandidate3 + ": " + candidate3Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }

    // Compare 1 to 4
    // why is the sky blue
    @GET
    @Path("/compare1to4Sky")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to4Sky() {
        String candidate1Response = aicandidate1.request(WHY_IS_THE_SKY_BLUE);
        String candidate4Response = aicandidate2.request(WHY_IS_THE_SKY_BLUE);

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate4Response);

        System.out.println(response.output());
        System.out.println(modelNameCandidate1 + ": " + candidate1Response + "\n");
        System.out.println(modelNameCandidate4 + ": " + candidate4Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }

    // Compare 1 to 2
    // who is Burr Sutter
    @GET
    @Path("/compare1to2Burr")
    @Produces(MediaType.TEXT_PLAIN)
    public String compare1to2Burr() {
        String candidate1Response = aicandidate1.request(WHO_IS_BURR_SUTTER);
        String candidate2Response = aicandidate2.request(WHO_IS_BURR_SUTTER);

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
        String candidate1Response = aicandidate1.request(WHO_IS_BURR_SUTTER);
        String candidate3Response = aicandidate3.request(WHO_IS_BURR_SUTTER);

        TestTwoStringsResponse response = aicosigntester.test(candidate1Response, candidate3Response);

        System.out.println(response.output());
        System.out.println(modelNameCandidate1 + ": " + candidate1Response + "\n");
        System.out.println(modelNameCandidate3 + ": " + candidate3Response + "\n");

        System.out.println(response.score());

        return "Score: " + response.score();
    }    

    // Compare Candidates to Judge
    // who is Burr Sutter
    @GET
    @Path("/comparetoJudgeBurr")
    @Produces(MediaType.TEXT_PLAIN)
    public String comparetoJudgeBurr() {
        String judgeResponse = aijudge.request(WHO_IS_BURR_SUTTER);

        // Candidate 1
        String candidate1Response = aicandidate1.request(WHO_IS_BURR_SUTTER);
        TestTwoStringsResponse test1response = aicosigntester.test(judgeResponse, candidate1Response);
        
        // Candidate 2
        String candidate2Response = aicandidate2.request(WHO_IS_BURR_SUTTER);
        TestTwoStringsResponse test2response = aicosigntester.test(judgeResponse, candidate2Response);
        
        // Candidate 3
        String candidate3Response = aicandidate3.request(WHO_IS_BURR_SUTTER);
        TestTwoStringsResponse test3response = aicosigntester.test(judgeResponse, candidate3Response);

        // Candidate 4
        String candidate4Response = aicandidate4.request(WHO_IS_BURR_SUTTER);
        TestTwoStringsResponse test4response = aicosigntester.test(judgeResponse, candidate4Response);


        String judgePrompt = "Which of the following numbers is the highest score: " +
            test1response.score() + " " +
            test2response.score() + " " +
            test3response.score() + " " +
            test4response.score();

        String judgedWinner = aijudge.request(judgePrompt);

        StringBuilder output = new StringBuilder();

        System.out.println("\n******\n");

        output.append("\nJudge: " + judgeResponse + "\n");
        // System.out.println("\nJudge:" + judgeResponse + "\n");

        output.append("\nCandiate 1 (" + modelNameCandidate1 + ") ");        
        output.append("\n" + test1response.output());
        output.append("\nCandidate 1: " + candidate1Response + "\n");
        output.append(test1response.score());
        
        output.append("\nCandiate 2 (" + modelNameCandidate2 + ") ");
        output.append("\n" + test2response.output());
        output.append("\nCandidate 2: " + candidate2Response + "\n");
        output.append(test2response.score());

        output.append("\nCandiate 3 (" + modelNameCandidate3 + ") ");
        output.append("\n" + test3response.output());
        output.append("\nCandidate 3: " + candidate3Response + "\n");
        output.append(test3response.score());

        output.append("\nCandiate 4 (" + modelNameCandidate4 + ") ");
        output.append("\n" + test4response.output());
        output.append("\nCandidate 4: " + candidate4Response + "\n");
        output.append(test4response.score());
        
        System.out.println(output.toString());

        System.out.println("\n\n" + judgePrompt);
        // output.append("\n\n" + judgePrompt);

        System.out.println(judgedWinner);
        output.append("\n" + judgedWinner);

        return output.toString();
        
    }

    // Compare Candidates to Judge
    // why is the sky blue?
    @GET
    @Path("/comparetoJudgeSky")
    @Produces(MediaType.TEXT_PLAIN)
    public String comparetoJudgeSky() {
        String judgeResponse = aijudge.request(WHY_IS_THE_SKY_BLUE);

        // Candidate 1
        String candidate1Response = aicandidate1.request(WHY_IS_THE_SKY_BLUE);
        TestTwoStringsResponse test1response = aicosigntester.test(judgeResponse, candidate1Response);
        
        // Candidate 2
        String candidate2Response = aicandidate2.request(WHY_IS_THE_SKY_BLUE);
        TestTwoStringsResponse test2response = aicosigntester.test(judgeResponse, candidate2Response);
        
        // Candidate 3
        String candidate3Response = aicandidate3.request(WHY_IS_THE_SKY_BLUE);
        TestTwoStringsResponse test3response = aicosigntester.test(judgeResponse, candidate3Response);

        // Candidate 4
        String candidate4Response = aicandidate4.request(WHY_IS_THE_SKY_BLUE);
        TestTwoStringsResponse test4response = aicosigntester.test(judgeResponse, candidate4Response);


        String judgePrompt = "Which of the following numbers is the highest score: " +
            test1response.score() + " " +
            test2response.score() + " " +
            test3response.score() + " " +
            test4response.score();

        String judgedWinner = aijudge.request(judgePrompt);

        StringBuilder output = new StringBuilder();

        System.out.println("\n******\n");

        output.append("\nJudge: " + judgeResponse + "\n");
        // System.out.println("\nJudge:" + judgeResponse + "\n");

        output.append("\nCandiate 1 (" + modelNameCandidate1 + ") ");        
        output.append("\n" + test1response.output());
        output.append("\nCandidate 1: " + candidate1Response + "\n");
        output.append(test1response.score());
        
        output.append("\nCandiate 2 (" + modelNameCandidate2 + ") ");
        output.append("\n" + test2response.output());
        output.append("\nCandidate 2: " + candidate2Response + "\n");
        output.append(test2response.score());

        output.append("\nCandiate 3 (" + modelNameCandidate3 + ") ");
        output.append("\n" + test3response.output());
        output.append("\nCandidate 3: " + candidate3Response + "\n");
        output.append(test3response.score());

        output.append("\nCandiate 4 (" + modelNameCandidate4 + ") ");
        output.append("\n" + test4response.output());
        output.append("\nCandidate 4: " + candidate4Response + "\n");
        output.append(test4response.score());
        
        System.out.println(output.toString());

        System.out.println("\n\n" + judgePrompt);
        // output.append("\n\n" + judgePrompt);

        System.out.println(judgedWinner);
        output.append("\n" + judgedWinner);

        return output.toString();
        
    }

}

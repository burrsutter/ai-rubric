package com.burrsutter;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/")
public class RESTResource {

    @Inject AIGreetingService aigreeter;

    @Inject AIJSONService aijsongreeter;

    @Inject AITestTwoStringsService aicosigntester;

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

    @GET
    @Path("/bye")
    @Produces(MediaType.TEXT_PLAIN)
    public String bye() {
        return "Aloha";
    }

}

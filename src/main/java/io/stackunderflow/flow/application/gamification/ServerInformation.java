package io.stackunderflow.flow.application.gamification;

public class ServerInformation {
    final static public int PORT = Integer.parseInt(System.getenv("GAMIFICATION_PORT"));
    final static public String ADDRESS = System.getenv("GAMIFICATION_ADDRESS");
    final static public String apiURL = "http://" + ADDRESS + ":" + PORT;
    final static public String apiKey = System.getenv("GAMIFICATION_TOKEN");
    final static public int upvoteEventType = Integer.parseInt(System.getenv("UPVOTE_EVENTTYPE"));
    final static public int proposeQuestionEventType = Integer.parseInt(System.getenv("NEW_QUESTION_EVENTTYPE"));

    // badge : 76
    // event: 21
}

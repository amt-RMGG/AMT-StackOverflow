package io.stackunderflow.flow.application.gamification;

public class ServerInformation {
    final static public int PORT = 8090;
    final static public String ADRESS = "host.docker.internal";
    final static public String apiURL = "http://" + ADRESS + ":" + PORT;
    final static public String apiKey = "c4287505-9e1b-4d85-9563-c8a32727b58a";
    final static public int upvoteEventType = 3;
    final static public int proposeQuestionEventType = 22;
    // badge : 76
    // event: 21
}

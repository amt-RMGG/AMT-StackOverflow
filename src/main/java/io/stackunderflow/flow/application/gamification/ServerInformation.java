package io.stackunderflow.flow.application.gamification;

public class ServerInformation {
    final static public int PORT = 8090;
    final static public String ADRESS = "localhost";
    final static public String apiURL = "http://" + ADRESS + ":" + PORT;
    final static public String apiKey = "ff1165b6-fc1a-44a8-a228-7a2fe9756c18";
    final static public int upvoteEventType = 21;

    //Badge - id : 87
    //Event - upvote - id = 21
    //Rule : badge reward = 87 , eventType = 21 , threshold = 2
}

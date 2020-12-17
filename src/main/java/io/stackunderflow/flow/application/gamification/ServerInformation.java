package io.stackunderflow.flow.application.gamification;

import javax.enterprise.context.Dependent;

public class ServerInformation {
    final static public int PORT = 8090;
    final static public String ADRESS = "localhost";
    final static public String apiURL = "http://" + ADRESS + ":" + PORT;
    final static public String apiKey = "fcf02ac4-372e-4923-a0d3-7f375dec06d0";
    final static public int upvoteEventType = 4;
}

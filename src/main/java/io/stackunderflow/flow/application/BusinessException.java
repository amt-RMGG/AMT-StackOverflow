package io.stackunderflow.flow.application;

public class BusinessException extends Exception {

    //Pas montré dans le cours vidéo , je suppose qu'on fait un heritage "classique" d'exception
    public BusinessException(String message) {
        super(message);
    }
}

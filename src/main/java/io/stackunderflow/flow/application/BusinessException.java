package io.stackunderflow.flow.application;

public class BusinessException extends Exception {

    //Pas montre dans le cours video , je suppose qu'on fait un heritage "classique" d'exception
    public BusinessException(String message) {
        super(message);
    }
}

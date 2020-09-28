package io.stackunderflow.flow.application;

import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry {

    private static ServiceRegistry singleton; //Singleton, bad idea but will be change in the futur

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null)
            singleton = new ServiceRegistry();
        return singleton;
    }

    //Private constructor for singleton pattern, will be change in the futur
    private ServiceRegistry(){
        singleton = this;
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);
    }

    public QuestionFacade getQuestionFacade() { return questionFacade; }

}

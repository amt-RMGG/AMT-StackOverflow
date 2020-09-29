package io.stackunderflow.flow.application;

import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.infrastructure.persistence.memory.InMemoryPersonRepository;
import io.stackunderflow.flow.infrastructure.persistence.memory.InMemoryQuestionRepository;

public class ServiceRegistry {

    private static ServiceRegistry singleton; //Singleton, bad idea but will be change in the futur

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    private static IPersonRepository personRepository;
    private static IdentityManagementFacade identityManagementFacade;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null)
            singleton = new ServiceRegistry();
        return singleton;
    }

    //Private constructor for singleton pattern, will be change in the futur
    private ServiceRegistry(){
        singleton = this;

        //"DB" des questions
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository);

        //"DB" des users
        personRepository = new InMemoryPersonRepository();
        identityManagementFacade = new IdentityManagementFacade(personRepository);
    }

    public QuestionFacade getQuestionFacade() { return questionFacade; }

    public IdentityManagementFacade getIdentityManagementFacade() {
        return identityManagementFacade;
    }
}

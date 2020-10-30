package io.stackunderflow.flow.application;

import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.UserFacade;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.infrastructure.persistence.IPersonRepository;
import io.stackunderflow.flow.infrastructure.persistence.IQuestionRepository;
import jdk.jfr.Name;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@ApplicationScoped
@Name("ServiceRegistry")
public class ServiceRegistry {

    @Inject
    @Named("JdbcQuestionRepository")
    private IQuestionRepository questionRepository;
    private QuestionFacade questionFacade;

    @Inject
    @Named("JdbcPersonRepository")
    private IPersonRepository personRepository;
    private IdentityManagementFacade identityManagementFacade;
    private UserFacade userFacade;


    @PostConstruct
    public void postConstruct(){
        questionFacade = new QuestionFacade(questionRepository);
        identityManagementFacade = new IdentityManagementFacade(personRepository);
        userFacade = new UserFacade(personRepository);
    }

    public QuestionFacade getQuestionFacade() { return questionFacade; }

    public IdentityManagementFacade getIdentityManagementFacade() {
        return identityManagementFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }
}

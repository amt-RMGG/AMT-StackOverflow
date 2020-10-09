package io.stackunderflow.flow.application;

import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
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

    @PostConstruct
    public void postConstruct(){
        questionFacade = new QuestionFacade(questionRepository);
        identityManagementFacade = new IdentityManagementFacade(personRepository);
    }

    public QuestionFacade getQuestionFacade() { return questionFacade; }

    public IdentityManagementFacade getIdentityManagementFacade() {
        return identityManagementFacade;
    }
}

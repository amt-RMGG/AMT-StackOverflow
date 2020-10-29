package io.stackunderflow.flow.application;

import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.UserFacade;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.application.vote.VoteFacade;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
import io.stackunderflow.flow.infrastructure.persistence.jdbc.JdbcPersonRepository;
import io.stackunderflow.flow.infrastructure.persistence.jdbc.JdbcQuestionRepository;
import io.stackunderflow.flow.infrastructure.persistence.memory.InMemoryPersonRepository;
import io.stackunderflow.flow.infrastructure.persistence.memory.InMemoryQuestionRepository;
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

    private static IVoteRepository voteRepository;
    private static VoteFacade voteFacade;

    public static ServiceRegistry getServiceRegistry() {
        if(singleton == null)
            singleton = new ServiceRegistry();
        return singleton;
    }

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

    public VoteFacade getVoteFacade() {
        return voteFacade;
    }

    public UserFacade getUserFacade() {
        return userFacade;
    }
}

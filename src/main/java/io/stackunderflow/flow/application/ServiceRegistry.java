package io.stackunderflow.flow.application;

import io.stackunderflow.flow.application.gamification.GamificationFacade;
import io.stackunderflow.flow.application.identitymgmt.IdentityManagementFacade;
import io.stackunderflow.flow.application.identitymgmt.UserFacade;
import io.stackunderflow.flow.application.question.QuestionFacade;
import io.stackunderflow.flow.application.vote.VoteFacade;
import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
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

    @Inject
    @Named("JdbcVoteRepository")
    private IVoteRepository voteRepository;
    private VoteFacade voteFacade;

    private GamificationFacade gamificationFacade;

    @PostConstruct
    public void postConstruct(){
        gamificationFacade = new GamificationFacade();
        questionFacade = new QuestionFacade(questionRepository);
        identityManagementFacade = new IdentityManagementFacade(personRepository);
        userFacade = new UserFacade(personRepository);
        voteFacade = new VoteFacade(voteRepository, gamificationFacade);
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

    public GamificationFacade getGamificationFacade() { return gamificationFacade; }
}

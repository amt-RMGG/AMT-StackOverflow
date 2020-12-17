package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.application.gamification.GamificationFacade;
import io.stackunderflow.flow.application.gamification.ServerInformation;
import io.stackunderflow.flow.application.identitymgmt.UserFacade;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.util.Hasher;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
import io.stackunderflow.flow.domain.vote.Vote;

import javax.inject.Inject;

public class VoteFacade {

    private IVoteRepository voteRepository;
    @Inject
    private GamificationFacade gamificationFacade;

    public VoteFacade(){}

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void proposeVote(ProposeVoteCommand command, boolean answer) {
        try {
            if(answer) {
                if(!voteRepository.checkIfVoteExistAnswer(command.getIdQuestion().asString(), command.getUsername())) {
                    Vote submittedVote = Vote.builder()
                            .username(command.getUsername())
                            .idQuestion(command.getIdQuestion())
                            .type(command.getType())
                            .build();
                    voteRepository.saveAnswerVote(submittedVote);

                    //Send a event to the gamification server
                    // TODO on devrait avoir un userId :/
                    // gamificationFacade.sendEvent(ServerInformation.upvoteEventType, ))
                }
            }
            else {
                if(!voteRepository.checkIfVoteExistQuestion(command.getIdQuestion().asString(), command.getUsername())) {
                    Vote submittedVote = Vote.builder()
                            .username(command.getUsername())
                            .idQuestion(command.getIdQuestion())
                            .type(command.getType())
                            .build();
                    voteRepository.save(submittedVote);
                }
            }
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
    }
}

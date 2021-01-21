package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.application.gamification.dto.Badge;
import io.stackunderflow.flow.application.gamification.GamificationFacade;
import io.stackunderflow.flow.application.gamification.ServerInformation;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
import io.stackunderflow.flow.domain.vote.Vote;

import java.util.Optional;

public class VoteFacade {

    private IVoteRepository voteRepository;
    private GamificationFacade gamificationFacade;

    public VoteFacade(){}

    public VoteFacade(IVoteRepository voteRepository, GamificationFacade gamificationFacade) {
        this.voteRepository = voteRepository;
        this.gamificationFacade = gamificationFacade;
    }

    public Optional<Badge> proposeVote(ProposeVoteCommand command, boolean answer) {
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
                    return gamificationFacade.sendEvent(command.getUsername(), ServerInformation.upvoteEventType);
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

                    //Send a event to the gamification server
                    return gamificationFacade.sendEvent(command.getUsername(), ServerInformation.upvoteEventType);
                }
            }
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

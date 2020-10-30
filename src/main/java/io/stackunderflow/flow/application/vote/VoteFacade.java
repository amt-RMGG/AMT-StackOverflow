package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
import io.stackunderflow.flow.domain.vote.Vote;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

public class VoteFacade {
    private IVoteRepository voteRepository;

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void proposeVote(ProposeVoteCommand command) {
        try {
            if(!voteRepository.checkIfVoteExistQuestion(command.getIdQuestion().asString(), command.getUsername())) {
                Vote submittedVote = Vote.builder()
                        .username(command.getUsername())
                        .idQuestion(command.getIdQuestion())
                        .type(command.getType())
                        .build();
                voteRepository.save(submittedVote);
            }
        } catch (RegistrationFailedException e) {
            e.printStackTrace();
        }
    }
}

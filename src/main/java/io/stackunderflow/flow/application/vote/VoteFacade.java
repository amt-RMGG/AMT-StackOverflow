package io.stackunderflow.flow.application.vote;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.vote.IVoteRepository;
import io.stackunderflow.flow.domain.vote.Vote;

public class VoteFacade {
    private IVoteRepository voteRepository;

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void proposeVote(ProposeVoteCommand command) throws RegistrationFailedException {
        Vote submittedVote = Vote.builder()
                .idUser(command.getUserID())
                .idQuestion(command.getQuestionID())
                .type(command.getType())
                .build();
        voteRepository.save(submittedVote);
    }
}

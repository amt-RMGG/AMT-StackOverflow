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

    public void proposeVote(ProposeVoteCommand command) throws RegistrationFailedException {
        Vote submittedVote = Vote.builder()
                .username(command.getUsername())
                .idQuestion(command.getIdQuestion())
                .type(command.getType())
                .build();
        voteRepository.save(submittedVote);
    }

    public VoteDTO getVotes(VoteQuery query) {
        Collection<Vote> allVotes = voteRepository.find(query);

        List<VoteDTO.ProposedVoteDTO> allVotesDTO = allVotes.stream()
                                                            .map(vote -> VoteDTO.ProposedVoteDTO.builder()
                                                                 .username(vote.getUsername())
                                                                 .questionID(vote.getIdQuestion())
                                                                 .type(vote.getType()).build())
                                                            .collect(Collectors.toList());

        return VoteDTO.builder().votes(allVotesDTO).build();
    }
}

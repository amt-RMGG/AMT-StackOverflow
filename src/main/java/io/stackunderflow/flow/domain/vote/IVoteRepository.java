package io.stackunderflow.flow.domain.vote;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.vote.VoteQuery;
import io.stackunderflow.flow.domain.IRepository;

import java.util.Collection;

public interface IVoteRepository extends IRepository<Vote, VoteID> {
    public Collection<Vote> find(VoteQuery query);
    public void saveAnswerVote(Vote entity) throws RegistrationFailedException;
}

package io.stackunderflow.flow.domain.vote;

import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.application.vote.VoteQuery;
import io.stackunderflow.flow.domain.IRepository;
import io.stackunderflow.flow.domain.question.Question;

import java.util.Collection;

public interface IVoteRepository extends IRepository<Vote, VoteID> {
    public Collection<Vote> find(VoteQuery query);
}

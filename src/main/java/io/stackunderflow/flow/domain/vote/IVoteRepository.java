package io.stackunderflow.flow.domain.vote;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.vote.VoteQuery;
import io.stackunderflow.flow.domain.IRepository;
import io.stackunderflow.flow.domain.answer.AnswerId;
import io.stackunderflow.flow.domain.question.QuestionId;

import java.util.Collection;

public interface IVoteRepository extends IRepository<Vote, VoteID> {
    public Collection<Vote> find(VoteQuery query);
    public void saveAnswerVote(Vote entity) throws RegistrationFailedException;
    public boolean checkIfVoteExistQuestion(String idQuestion, String username);
    public boolean checkIfVoteExistAnswer(String idAnswer, String username);
}

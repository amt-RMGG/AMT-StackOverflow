package io.stackunderflow.flow.domain.question;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.IRepository;
import io.stackunderflow.flow.domain.answer.Answer;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;

import java.util.Collection;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {
    public Collection<Question> find(QuestionQuery query);

    public Collection<Question> search(String result);
    public void saveAnswer(Answer entity) throws RegistrationFailedException;
}

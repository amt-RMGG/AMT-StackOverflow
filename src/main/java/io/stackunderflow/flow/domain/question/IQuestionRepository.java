package io.stackunderflow.flow.domain.question;

import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.IRepository;

import java.util.Collection;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {
    public Collection<Question> find(QuestionQuery query);

    public Collection<Question> search(String result);
}

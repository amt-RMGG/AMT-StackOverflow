package io.stackunderflow.flow.infrastructure.persistence.memory;

import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.question.IQuestionRepository;
import io.stackunderflow.flow.domain.question.Question;
import io.stackunderflow.flow.domain.question.QuestionId;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository{

    @Override
    public Collection<Question> find(QuestionQuery query) {
        // behavior here ... TODO to be define !
        if(query != null){
            return findAll();
        }
        //if nothing is found, return the whole repo
        return findAll();
    }

    @Override
    public Collection<Question> search(String result) {
        return null;
    }

}

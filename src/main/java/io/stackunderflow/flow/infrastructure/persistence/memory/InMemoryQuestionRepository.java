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

public class InMemoryQuestionRepository implements IQuestionRepository {

    private Map<QuestionId, Question> store = new ConcurrentHashMap<>();

    @Override
    public void save(Question question) {
        store.put(question.getId(), question);
    }

    @Override
    public void remove(QuestionId questionId) {
        store.remove(questionId);
    }

    @Override
    public Optional<Question> findById(QuestionId questionId) {
        Question question = store.get(questionId);
        if(question == null)
            return Optional.empty();

        Question clonedQuestion = question.toBuilder().build();
        return Optional.of(clonedQuestion);
    }

    @Override
    public Collection<Question> find(QuestionQuery query) {
        if(query != null){
            // behavior here ... TODO to be define !
        }
        //if nothing is found, return the whole repo
        return findAll();
    }

    @Override
    public Collection<Question> findAll() {
        //Same as findById, we clone every question in the repo to return it
        //using java stream (Donini should be happy :p )
        return store.values()
                .stream()
                .map(question -> question
                                .toBuilder()
                                .build())
                                .collect(Collectors.toList());
    }
}

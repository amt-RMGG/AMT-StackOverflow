package io.stackunderflow.flow.domain.person;

import io.stackunderflow.flow.application.identitymgmt.UserQuery;
import io.stackunderflow.flow.application.question.QuestionQuery;
import io.stackunderflow.flow.domain.IRepository;

import java.util.Optional;


public interface IPersonRepository extends IRepository<Person, PersonId> {
    Optional<Person> findByUsername(String username);
    //Optional<Person> findById(PersonId id);
}

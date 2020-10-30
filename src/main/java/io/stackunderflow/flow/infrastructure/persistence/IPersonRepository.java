package io.stackunderflow.flow.infrastructure.persistence;

import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.person.PersonId;

import java.util.Optional;


public interface IPersonRepository extends IRepository<Person, PersonId> {
    Optional<Person> findByUsername(String username);
    //Optional<Person> findById(PersonId id);
}

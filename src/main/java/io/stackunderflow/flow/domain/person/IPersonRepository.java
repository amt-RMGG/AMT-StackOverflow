package io.stackunderflow.flow.domain.person;

import io.stackunderflow.flow.domain.IRepository;

import java.util.Optional;


public interface IPersonRepository extends IRepository<Person, PersonId> {
    public Optional<Person> findByUsername(String username);
}

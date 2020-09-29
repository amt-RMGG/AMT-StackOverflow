package io.stackunderflow.flow.infrastructure.persistence.memory;

import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.person.PersonId;

import java.util.Optional;

public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {

    //TODO : DO ITTT
    @Override
    public Optional<Person> findByUsername(String username) {
        return Optional.empty();
    }
}

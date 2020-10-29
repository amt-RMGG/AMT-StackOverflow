package io.stackunderflow.flow.domain.person;
import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;
import io.stackunderflow.flow.domain.IRepository;

import java.util.Collection;
import java.util.Optional;


public interface IPersonRepository {
    Optional<Person> findByUsername(String username);
    public void save(Person entity) throws RegistrationFailedException;
    public Collection<Person> findAll();
}

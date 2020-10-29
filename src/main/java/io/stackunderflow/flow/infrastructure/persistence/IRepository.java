package io.stackunderflow.flow.domain;

import io.stackunderflow.flow.application.identitymgmt.login.RegistrationFailedException;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<ENTITY extends IEntity ,ID extends Id> {

    public void save(ENTITY entity) throws RegistrationFailedException;
    public void remove(ID id);
    public Optional<ENTITY> findById(ID id);
    public Collection<ENTITY> findAll();
}

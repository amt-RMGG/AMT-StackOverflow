package io.stackunderflow.flow.infrastructure.persistence.memory;

import io.stackunderflow.flow.domain.person.IPersonRepository;
import io.stackunderflow.flow.domain.person.Person;
import io.stackunderflow.flow.domain.person.PersonId;


import java.util.Optional;
import java.util.Collection;

public class InMemoryPersonRepository extends InMemoryRepository<Person, PersonId> implements IPersonRepository {


    @Override
    public void save(Person entity){
        // enforce unique username
        synchronized (entity.getUsername()){
            if(!findByUsername(entity.getUsername()).isEmpty()){
                //Pourquoi dans la vidéo il a pas de problème ?
                //throw new IntegrityConstraintViolationException("Cannot save/update person. Integrity constraint violation : username");
                return;
            }
            super.save(entity);
        }
    }

    @Override
    public Optional<Person> findByUsername(String username) {

        //TODO Dans la vidéo comme ça, mais ne marche pas! Pourquoi ?
     /*   List<Person> matchingEntities = findAll().stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());*/

        //Solution altérnative du stream qui fait chier...
        Collection matchingEntities = findAll();
        for(Object p : matchingEntities){
            if(((Person)p).getUsername().equals(username)){
                return Optional.of(((Person)p).deepClone());
            }
        }

        return Optional.empty();
    }
}

package io.stackunderflow.flow.infrastructure.persistence.memory;

import io.stackunderflow.flow.domain.IEntity;
import io.stackunderflow.flow.domain.IRepository;
import io.stackunderflow.flow.domain.Id;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> implements IRepository<ENTITY, ID> {


    private Map<ID, ENTITY> store = new ConcurrentHashMap<>();

    public void save(ENTITY entity) {
        entity.getId(); //Dans la vidéo comme ça, mais pourquoi ?!
        store.put(entity.getId(), entity);
    }

    public void remove(ID id) {
        store.remove(id);
    }

    public Optional<ENTITY> findById(ID id) {
        ENTITY existingEntity = store.get(id);
        if(existingEntity == null)
            return Optional.empty();

        ENTITY clonedEntity = existingEntity.deepClone();
        return Optional.of(clonedEntity);
    }

    @Override
    public Collection findAll() {
        return store.values().stream()
                .map(entity -> entity.deepClone())
                .collect(Collectors.toList());
    }
}

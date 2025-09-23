package com.gymcrm.dao;

import com.gymcrm.model.Trainer;
import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TrainerDAO extends AbstractDAO<Trainer> implements UserDAO<Trainer> {

    private static final String NAMESPACE = "trainers";
    private final AtomicLong idCounter = new AtomicLong(1);

    @Override
    protected String getNamespace() {
        return NAMESPACE;
    }

    @Override
    protected Long getNextId() {
        return idCounter.getAndIncrement();
    }

    @Override
    protected Long getId(Trainer trainer) {
        return trainer.getUserId();
    }

    @Override
    protected void setId(Trainer trainer, Long id) {
        trainer.setUserId(id);
    }

    @Override
    public Trainer findByUsername(String username) {
        return (Trainer) storage.getEntities(getNamespace()).values().stream()
                .filter(obj -> obj instanceof Trainer)
                .map(obj -> (Trainer) obj)
                .filter(trainer -> trainer.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
package com.gymcrm.dao;

import com.gymcrm.model.Training;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TrainingDAO extends AbstractDAO<Training> {

    private static final String NAMESPACE = "trainings";
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
    protected Long getId(Training training) {
        return training.getId();
    }

    @Override
    protected void setId(Training training, Long id) {
        training.setId(id);
    }
}
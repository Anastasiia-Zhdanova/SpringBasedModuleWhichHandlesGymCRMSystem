package com.gymcrm.dao;

import com.gymcrm.model.Trainee;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicLong;

@Repository
public class TraineeDAO extends AbstractDAO<Trainee> implements UserDAO<Trainee> {

    private static final String NAMESPACE = "trainees";
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
    protected Long getId(Trainee trainee) {
        return trainee.getUserId();
    }

    @Override
    protected void setId(Trainee trainee, Long id) {
        trainee.setUserId(id);
    }
}
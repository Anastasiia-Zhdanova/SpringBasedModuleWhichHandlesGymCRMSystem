package com.gymcrm.service;

import com.gymcrm.dao.TrainerDAO;
import com.gymcrm.model.Trainer;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest extends AbstractUserServiceTest<Trainer, TrainerService, TrainerDAO> {
    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    private TrainerService trainerService;

    @Override
    protected void setupMocks() {
        this.userDAO = trainerDAO;
        this.userService = trainerService;
    }

    @Override
    protected Trainer createNewUserInstance() {
        return new Trainer();
    }

    @Override
    protected Trainer callSpecificUpdate(Trainer user) {
        return trainerService.updateTrainer(user);
    }

    @Override
    protected void callSpecificDelete(Long userId) {
        trainerService.deleteTrainer(userId);
    }

    @Override
    protected Trainer callSpecificFindById(Long userId) {
        return trainerService.findTrainerById(userId);
    }
}
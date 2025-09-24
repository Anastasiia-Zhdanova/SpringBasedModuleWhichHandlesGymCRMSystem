package com.gymcrm.service;

import com.gymcrm.dao.TraineeDAO;
import com.gymcrm.model.Trainee;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest extends AbstractUserServiceTest<Trainee, TraineeService, TraineeDAO> {
    @Mock
    private TraineeDAO traineeDAO;

    @InjectMocks
    private TraineeService traineeService;

    @Override
    protected void setupMocks() {
        this.userDAO = traineeDAO;
        this.userService = traineeService;
    }

    @Override
    protected Trainee createNewUserInstance() {
        return new Trainee();
    }

    @Override
    protected Trainee callSpecificUpdate(Trainee user) {
        return traineeService.updateTrainee(user);
    }

    @Override
    protected void callSpecificDelete(Long userId) {
        traineeService.deleteTrainee(userId);
    }

    @Override
    protected Trainee callSpecificFindById(Long userId) {
        return traineeService.findTraineeById(userId);
    }
}
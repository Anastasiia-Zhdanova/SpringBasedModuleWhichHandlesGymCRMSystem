package com.gymcrm.facade;

import com.gymcrm.model.Trainee;
import com.gymcrm.model.Trainer;
import com.gymcrm.model.Training;
import com.gymcrm.service.TraineeService;
import com.gymcrm.service.TrainerService;
import com.gymcrm.service.TrainingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GymFacadeTest {

    @Mock
    private TraineeService traineeService;
    @Mock
    private TrainerService trainerService;
    @Mock
    private TrainingService trainingService;

    @InjectMocks
    private GymFacade gymFacade;

    private Trainee trainee;
    private Trainer trainer;
    private Training training;
    private final Long id = 1L;

    @BeforeEach
    void setUp() {
        trainee = new Trainee();
        trainer = new Trainer();
        training = new Training();
    }

    @Test
    void createTrainee_ShouldDelegateToService() {
        gymFacade.createTrainee(trainee);
        verify(traineeService, times(1)).create(trainee);
    }

    @Test
    void updateTrainee_ShouldDelegateToService() {
        when(traineeService.update(trainee)).thenReturn(trainee);
        gymFacade.updateTrainee(trainee);
        verify(traineeService, times(1)).update(trainee);
    }

    @Test
    void deleteTrainee_ShouldDelegateToService() {
        gymFacade.deleteTrainee(id);
        verify(traineeService, times(1)).delete(id);
    }

    @Test
    void findTraineeById_ShouldDelegateToService() {
        when(traineeService.findById(id)).thenReturn(trainee);
        Trainee found = gymFacade.findTraineeById(id);
        assertNotNull(found);
        verify(traineeService, times(1)).findById(id);
    }

    @Test
    void createTrainer_ShouldDelegateToService() {
        gymFacade.createTrainer(trainer);
        verify(trainerService, times(1)).create(trainer);
    }

    @Test
    void updateTrainer_ShouldDelegateToService() {
        when(trainerService.update(trainer)).thenReturn(trainer);
        gymFacade.updateTrainer(trainer);
        verify(trainerService, times(1)).update(trainer);
    }

    @Test
    void deleteTrainer_ShouldDelegateToService() {
        gymFacade.deleteTrainer(id);
        verify(trainerService, times(1)).delete(id);
    }

    @Test
    void findTrainerById_ShouldDelegateToService() {
        when(trainerService.findById(id)).thenReturn(trainer);
        Trainer found = gymFacade.findTrainerById(id);
        assertNotNull(found);
        verify(trainerService, times(1)).findById(id);
    }

    @Test
    void createTraining_ShouldDelegateToService() {
        gymFacade.createTraining(training);
        verify(trainingService, times(1)).createTraining(training);
    }

    @Test
    void selectTraining_ShouldDelegateToService() {
        when(trainingService.selectTraining(id)).thenReturn(training);
        Training found = gymFacade.selectTraining(id);
        assertNotNull(found);
        verify(trainingService, times(1)).selectTraining(id);
    }
}
package com.gymcrm.service;

import com.gymcrm.dao.TrainingDAO;
import com.gymcrm.model.Training;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

    @Mock
    private TrainingDAO trainingDAO;

    @InjectMocks
    private TrainingService trainingService;

    @Test
    void createTraining_ShouldCallDao() {
        Training training = new Training();
        trainingService.createTraining(training);
        verify(trainingDAO, times(1)).create(training);
    }

    @Test
    void selectTraining_ShouldCallDao_AndReturnTraining() {
        long trainingId = 1L;
        Training expectedTraining = new Training();

        when(trainingDAO.findById(trainingId)).thenReturn(expectedTraining);

        Training foundTraining = trainingService.selectTraining(trainingId);
        verify(trainingDAO, times(1)).findById(trainingId);
        assertNotNull(foundTraining);
        assertEquals(expectedTraining, foundTraining);
    }

    @Test
    void selectTraining_ShouldReturnNull_WhenTrainingNotFound() {
        long nonExistentTrainingId = 999L;
        when(trainingDAO.findById(nonExistentTrainingId)).thenReturn(null);

        Training foundTraining = trainingService.selectTraining(nonExistentTrainingId);

        assertNull(foundTraining);
        verify(trainingDAO, times(1)).findById(nonExistentTrainingId);
    }
}
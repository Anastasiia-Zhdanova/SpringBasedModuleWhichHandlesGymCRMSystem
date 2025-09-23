package com.gymcrm.service;

import com.gymcrm.dao.TraineeDAO;
import com.gymcrm.model.Trainee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TraineeServiceTest {

    @Mock
    private TraineeDAO traineeDAO;

    @InjectMocks
    private TraineeService traineeService;

    @Test
    void createTrainee_ShouldGenerateUsernameAndPassword() {
        Trainee trainee = new Trainee();
        trainee.setFirstName("John");
        trainee.setLastName("Doe");

        traineeService.createTrainee(trainee);

        assertNotNull(trainee.getUsername());
        assertNotNull(trainee.getPassword());
        assertEquals("john.doe", trainee.getUsername());
        assertEquals(10, trainee.getPassword().length());
        verify(traineeDAO, times(1)).create(trainee);
    }

    @Test
    void updateTrainee_ShouldCallDao_AndReturnUpdatedTrainee() {
        Trainee trainee = new Trainee();
        when(traineeDAO.update(trainee)).thenReturn(trainee);
        Trainee updated = traineeService.updateTrainee(trainee);
        verify(traineeDAO, times(1)).update(trainee);
        assertEquals(trainee, updated);
    }

    @Test
    void updateTrainee_ShouldReturnNull_WhenTraineeNotFound() {
        Trainee trainee = new Trainee();
        when(traineeDAO.update(trainee)).thenReturn(null);
        Trainee updated = traineeService.updateTrainee(trainee);
        assertNull(updated);
        verify(traineeDAO, times(1)).update(trainee);
    }

    @Test
    void deleteTrainee_ShouldCallDao() {
        Long traineeId = 1L;
        traineeService.deleteTrainee(traineeId);
        verify(traineeDAO, times(1)).delete(traineeId);
    }

    @Test
    void findTraineeById_ShouldCallDao_AndReturnTrainee() {
        Long traineeId = 1L;
        Trainee expectedTrainee = new Trainee();
        when(traineeDAO.findById(traineeId)).thenReturn(expectedTrainee);
        Trainee foundTrainee = traineeService.findTraineeById(traineeId);
        verify(traineeDAO, times(1)).findById(traineeId);
        assertEquals(expectedTrainee, foundTrainee);
    }

    @Test
    void findTraineeById_ShouldReturnNull_WhenTraineeNotFound() {
        Long traineeId = 1L;
        when(traineeDAO.findById(traineeId)).thenReturn(null);
        Trainee foundTrainee = traineeService.findTraineeById(traineeId);
        assertNull(foundTrainee);
        verify(traineeDAO, times(1)).findById(traineeId);
    }
}
package com.gymcrm.service;

import com.gymcrm.dao.TraineeDAO;
import com.gymcrm.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
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

    private Trainee trainee;
    private final Long userId = 1L;

    @BeforeEach
    void setUp() {
        trainee = new Trainee();
        trainee.setUserId(userId);
    }

    @Test
    void updateTrainee_ShouldReturnUpdatedTrainee_WhenTraineeExists() {
        when(traineeDAO.update(trainee)).thenReturn(trainee);
        Trainee updated = traineeService.updateTrainee(trainee);
        assertNotNull(updated);
        verify(traineeDAO, times(1)).update(trainee);
    }

    @Test
    void updateTrainee_ShouldReturnNull_WhenTraineeDoesNotExist() {
        when(traineeDAO.update(trainee)).thenReturn(null);
        Trainee updated = traineeService.updateTrainee(trainee);
        assertNull(updated);
        verify(traineeDAO, times(1)).update(trainee);
    }

    @Test
    void deleteTrainee_ShouldCallDeleteInAbstractClass() {
        doNothing().when(traineeDAO).delete(userId);
        traineeService.deleteTrainee(userId);
        verify(traineeDAO, times(1)).delete(userId);
    }

    @Test
    void findTraineeById_ShouldReturnTrainee_WhenTraineeExists() {
        when(traineeDAO.findById(userId)).thenReturn(trainee);
        Trainee found = traineeService.findTraineeById(userId);
        assertNotNull(found);
        assertEquals(userId, found.getUserId());
        verify(traineeDAO, times(1)).findById(userId);
    }

    @Test
    void findTraineeById_ShouldReturnNull_WhenTraineeDoesNotExist() {
        when(traineeDAO.findById(userId)).thenReturn(null);
        Trainee found = traineeService.findTraineeById(userId);
        assertNull(found);
        verify(traineeDAO, times(1)).findById(userId);
    }
}
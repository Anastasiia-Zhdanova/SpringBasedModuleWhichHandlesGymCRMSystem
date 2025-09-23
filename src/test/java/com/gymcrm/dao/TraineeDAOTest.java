package com.gymcrm.dao;

import com.gymcrm.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TraineeDAOTest {

    @Mock
    private InMemoryStorage storage;

    @InjectMocks
    private TraineeDAO traineeDAO;

    private Map<Long, Object> mockTraineesMap;

    @BeforeEach
    public void setUp() {
        mockTraineesMap = new HashMap<>();
        when(storage.getEntities("trainees")).thenReturn(mockTraineesMap);
    }

    @Test
    void create_ShouldGenerateIdAndSaveTrainee() {
        Trainee trainee = new Trainee();
        traineeDAO.create(trainee);
        assertNotNull(trainee.getUserId());
        assertFalse(mockTraineesMap.isEmpty());
        assertEquals(1, mockTraineesMap.size());
        assertEquals(trainee, mockTraineesMap.get(trainee.getUserId()));
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void findById_ShouldReturnTrainee_WhenTraineeExists() {
        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        mockTraineesMap.put(1L, trainee);
        Trainee foundTrainee = traineeDAO.findById(1L);
        assertNotNull(foundTrainee);
        assertEquals(trainee, foundTrainee);
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void findById_ShouldReturnNull_WhenTraineeDoesNotExist() {
        Trainee foundTrainee = traineeDAO.findById(99L);
        assertNull(foundTrainee);
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void update_ShouldReturnUpdatedTrainee_WhenTraineeExists() {
        Trainee originalTrainee = new Trainee();
        originalTrainee.setUserId(1L);
        mockTraineesMap.put(1L, originalTrainee);
        Trainee updatedTrainee = new Trainee();
        updatedTrainee.setUserId(1L);
        Trainee result = traineeDAO.update(updatedTrainee);
        assertNotNull(result);
        assertEquals(updatedTrainee, result);
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void update_ShouldReturnNull_WhenTraineeDoesNotExist() {
        Trainee traineeToUpdate = new Trainee();
        traineeToUpdate.setUserId(99L);
        Trainee result = traineeDAO.update(traineeToUpdate);
        assertNull(result);
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void delete_ShouldRemoveTrainee_WhenTraineeExists() {
        Trainee trainee = new Trainee();
        trainee.setUserId(1L);
        mockTraineesMap.put(1L, trainee);
        traineeDAO.delete(1L);
        assertFalse(mockTraineesMap.containsKey(1L));
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void delete_ShouldDoNothing_WhenTraineeDoesNotExist() {
        traineeDAO.delete(99L);
        assertTrue(mockTraineesMap.isEmpty());
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void findByUsername_ShouldReturnTrainee_WhenTraineeExists() {
        Trainee trainee = new Trainee();
        trainee.setUsername("john.doe");
        mockTraineesMap.put(1L, trainee);
        Trainee foundTrainee = traineeDAO.findByUsername("john.doe");
        assertNotNull(foundTrainee);
        assertEquals(trainee, foundTrainee);
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void findByUsername_ShouldReturnNull_WhenTraineeDoesNotExist() {
        Trainee foundTrainee = traineeDAO.findByUsername("nonexistent.user");
        assertNull(foundTrainee);
        verify(storage, times(1)).getEntities("trainees");
    }
}
package com.gymcrm.dao;

import com.gymcrm.model.Trainee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class TraineeDAOTest extends AbstractDAOTest<TraineeDAO, Trainee> {

    @InjectMocks
    private TraineeDAO traineeDAO;

    @Override
    protected String getNamespace() {
        return "trainees";
    }

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    void create_ShouldGenerateIdAndSaveTrainee() {
        Trainee trainee = new Trainee();
        traineeDAO.create(trainee);
        assertNotNull(trainee.getUserId());
        assertFalse(mockEntitiesMap.isEmpty());
        assertEquals(1, mockEntitiesMap.size());
        assertEquals(trainee, mockEntitiesMap.get(trainee.getUserId()));
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void findById_ShouldReturnTrainee_WhenTraineeExists() {
        Trainee existingTrainee = new Trainee();
        existingTrainee.setUserId(1L);
        mockEntitiesMap.put(1L, existingTrainee);
        Trainee foundTrainee = traineeDAO.findById(1L);
        assertNotNull(foundTrainee);
        assertEquals(existingTrainee, foundTrainee);
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
        originalTrainee.setFirstName("John");
        mockEntitiesMap.put(1L, originalTrainee);

        Trainee updatedTrainee = new Trainee();
        updatedTrainee.setUserId(1L);
        updatedTrainee.setFirstName("Jane");

        Trainee result = traineeDAO.update(updatedTrainee);
        assertNotNull(result);
        assertEquals("Jane", ((Trainee) mockEntitiesMap.get(1L)).getFirstName());
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
        mockEntitiesMap.put(1L, trainee);
        traineeDAO.delete(1L);
        assertFalse(mockEntitiesMap.containsKey(1L));
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void delete_ShouldDoNothing_WhenTraineeDoesNotExist() {
        traineeDAO.delete(99L);
        assertTrue(mockEntitiesMap.isEmpty());
        verify(storage, times(1)).getEntities("trainees");
    }

    @Test
    void findByUsername_ShouldReturnTrainee_WhenTraineeExists() {
        Trainee trainee = new Trainee();
        trainee.setUsername("john.doe");
        mockEntitiesMap.put(1L, trainee);
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
package com.gymcrm.dao;

import com.gymcrm.model.Training;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainingDAOTest extends AbstractDAOTest<TrainingDAO, Training> {

    @InjectMocks
    private TrainingDAO trainingDAO;

    @Override
    protected String getNamespace() {
        return "trainings";
    }

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
    }

    @Test
    void create_ShouldGenerateIdAndSaveTraining() {
        Training training = new Training();
        trainingDAO.create(training);
        assertNotNull(training.getId());
        assertFalse(mockEntitiesMap.isEmpty());
        assertEquals(1, mockEntitiesMap.size());
        assertEquals(training, mockEntitiesMap.get(training.getId()));
        verify(storage, times(1)).getEntities("trainings");
    }

    @Test
    void findById_ShouldReturnTraining_WhenTrainingExists() {
        Training existingTraining = new Training();
        existingTraining.setId(1L);
        mockEntitiesMap.put(1L, existingTraining);
        Training foundTraining = trainingDAO.findById(1L);
        assertNotNull(foundTraining);
        assertEquals(existingTraining, foundTraining);
        verify(storage, times(1)).getEntities("trainings");
    }

    @Test
    void findById_ShouldReturnNull_WhenTrainingDoesNotExist() {
        Training foundTraining = trainingDAO.findById(99L);
        assertNull(foundTraining);
        verify(storage, times(1)).getEntities("trainings");
    }

    @Test
    void update_ShouldReturnUpdatedTraining_WhenTrainingExists() {
        Training originalTraining = new Training();
        originalTraining.setId(1L);
        originalTraining.setTrainingName("Cardio");
        mockEntitiesMap.put(1L, originalTraining);

        Training updatedTraining = new Training();
        updatedTraining.setId(1L);
        updatedTraining.setTrainingName("Strength");

        Training result = trainingDAO.update(updatedTraining);
        assertNotNull(result);
        assertEquals("Strength", ((Training) mockEntitiesMap.get(1L)).getTrainingName());
        assertEquals(updatedTraining, result);
        verify(storage, times(1)).getEntities("trainings");
    }

    @Test
    void update_ShouldReturnNull_WhenTrainingDoesNotExist() {
        Training trainingToUpdate = new Training();
        trainingToUpdate.setId(99L);
        Training result = trainingDAO.update(trainingToUpdate);
        assertNull(result);
        verify(storage, times(1)).getEntities("trainings");
    }

    @Test
    void delete_ShouldRemoveTraining_WhenTrainingExists() {
        Training training = new Training();
        training.setId(1L);
        mockEntitiesMap.put(1L, training);
        trainingDAO.delete(1L);
        assertFalse(mockEntitiesMap.containsKey(1L));
        verify(storage, times(1)).getEntities("trainings");
    }

    @Test
    void delete_ShouldDoNothing_WhenTrainingDoesNotExist() {
        trainingDAO.delete(99L);
        assertTrue(mockEntitiesMap.isEmpty());
        verify(storage, times(1)).getEntities("trainings");
    }
}
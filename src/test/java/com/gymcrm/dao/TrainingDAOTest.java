package com.gymcrm.dao;

import com.gymcrm.model.Training;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingDAOTest {

    @Mock
    private InMemoryStorage storage;

    @InjectMocks
    private TrainingDAO trainingDAO;

    @Test
    public void testCreateTraining() {
        Map<Long, Object> trainingsMap = new HashMap<>();
        when(storage.getEntities("trainings")).thenReturn(trainingsMap);
        Training training = new Training();
        training.setTrainingName("Yoga");
        trainingDAO.create(training);
        assertFalse(trainingsMap.isEmpty());
    }

    @Test
    public void testFindById_TrainingExists() {
        Training existingTraining = new Training();
        Map<Long, Object> trainingsMap = new HashMap<>();
        trainingsMap.put(1L, existingTraining);
        when(storage.getEntities("trainings")).thenReturn(trainingsMap);
        Training foundTraining = trainingDAO.findById(1L);
        assertNotNull(foundTraining);
        assertEquals(existingTraining, foundTraining);
    }

    @Test
    public void testFindById_TrainingDoesNotExist() {
        Map<Long, Object> trainingsMap = new HashMap<>();
        when(storage.getEntities("trainings")).thenReturn(trainingsMap);
        Training foundTraining = trainingDAO.findById(1L);
        assertNull(foundTraining);
    }

    @Test
    public void testDeleteTraining() {
        Map<Long, Object> trainingsMap = new HashMap<>();
        Training training1 = new Training();
        training1.setId(1L);
        Training training2 = new Training();
        training2.setId(2L);
        trainingsMap.put(1L, training1);
        trainingsMap.put(2L, training2);
        when(storage.getEntities("trainings")).thenReturn(trainingsMap);
        assertEquals(2, trainingsMap.size());
        trainingDAO.delete(1L);
        assertEquals(1, trainingsMap.size());
        assertFalse(trainingsMap.containsKey(1L));
        assertTrue(trainingsMap.containsKey(2L));
    }
}
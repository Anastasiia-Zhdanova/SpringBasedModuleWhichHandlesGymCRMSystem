package com.gymcrm.dao;

import com.gymcrm.model.Trainer;
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
public class TrainerDAOTest {

    @Mock
    private InMemoryStorage storage;

    @InjectMocks
    private TrainerDAO trainerDAO;

    @Test
    public void testCreateTrainer() {
        Map<Long, Object> trainersMap = new HashMap<>();
        when(storage.getEntities("trainers")).thenReturn(trainersMap);
        Trainer trainer = new Trainer();
        trainer.setFirstName("Harry");
        trainer.setLastName("Potter");
        trainerDAO.create(trainer);
        assertNotNull(trainer.getUserId());
        assertTrue(trainersMap.containsKey(trainer.getUserId()));
        assertEquals(trainer, trainersMap.get(trainer.getUserId()));
    }

    @Test
    public void testFindById_TrainerExists() {
        Trainer existingTrainer = new Trainer();
        existingTrainer.setUserId(1L);
        Map<Long, Object> trainersMap = new HashMap<>();
        trainersMap.put(1L, existingTrainer);
        when(storage.getEntities("trainers")).thenReturn(trainersMap);
        Trainer foundTrainer = trainerDAO.findById(1L);
        assertNotNull(foundTrainer);
        assertEquals(1L, foundTrainer.getUserId());
    }

    @Test
    public void testDeleteTrainer() {
        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        Map<Long, Object> trainersMap = new HashMap<>();
        trainersMap.put(1L, trainer);
        when(storage.getEntities("trainers")).thenReturn(trainersMap);
        trainerDAO.delete(1L);
        assertFalse(trainersMap.containsKey(1L));
    }

    @Test public void testUpdateTrainer() {
        Map<Long, Object> trainersMap = new HashMap<>();
        when(storage.getEntities("trainers")).thenReturn(trainersMap);
        Trainer nonExistentTrainer = new Trainer();
        nonExistentTrainer.setUserId(99L);
        Trainer result = trainerDAO.update(nonExistentTrainer);
        assertNull(result);
    }
}
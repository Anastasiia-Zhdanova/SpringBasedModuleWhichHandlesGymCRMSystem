package com.gymcrm.dao;

import com.gymcrm.model.Trainer;
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
public class TrainerDAOTest {

    @Mock
    private InMemoryStorage storage;

    @InjectMocks
    private TrainerDAO trainerDAO;

    private Map<Long, Object> mockTrainersMap;

    @BeforeEach
    public void setUp() {
        mockTrainersMap = new HashMap<>();
        when(storage.getEntities("trainers")).thenReturn(mockTrainersMap);
    }

    @Test
    void create_ShouldGenerateIdAndSaveTrainer() {
        Trainer trainer = new Trainer();
        trainerDAO.create(trainer);
        assertNotNull(trainer.getUserId());
        assertFalse(mockTrainersMap.isEmpty());
        assertEquals(1, mockTrainersMap.size());
        assertEquals(trainer, mockTrainersMap.get(trainer.getUserId()));
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void findById_ShouldReturnTrainer_WhenTrainerExists() {
        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        mockTrainersMap.put(1L, trainer);
        Trainer foundTrainer = trainerDAO.findById(1L);
        assertNotNull(foundTrainer);
        assertEquals(trainer, foundTrainer);
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void findById_ShouldReturnNull_WhenTrainerDoesNotExist() {
        Trainer foundTrainer = trainerDAO.findById(99L);
        assertNull(foundTrainer);
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void update_ShouldReturnUpdatedTrainer_WhenTrainerExists() {
        Trainer originalTrainer = new Trainer();
        originalTrainer.setUserId(1L);
        mockTrainersMap.put(1L, originalTrainer);
        Trainer updatedTrainer = new Trainer();
        updatedTrainer.setUserId(1L);
        Trainer result = trainerDAO.update(updatedTrainer);
        assertNotNull(result);
        assertEquals(updatedTrainer, result);
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void update_ShouldReturnNull_WhenTrainerDoesNotExist() {
        Trainer trainerToUpdate = new Trainer();
        trainerToUpdate.setUserId(99L);
        Trainer result = trainerDAO.update(trainerToUpdate);
        assertNull(result);
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void delete_ShouldRemoveTrainer_WhenTrainerExists() {
        Trainer trainer = new Trainer();
        trainer.setUserId(1L);
        mockTrainersMap.put(1L, trainer);
        trainerDAO.delete(1L);
        assertFalse(mockTrainersMap.containsKey(1L));
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void delete_ShouldDoNothing_WhenTrainerDoesNotExist() {
        trainerDAO.delete(99L);
        assertTrue(mockTrainersMap.isEmpty());
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void findByUsername_ShouldReturnTrainer_WhenTrainerExists() {
        Trainer trainer = new Trainer();
        trainer.setUsername("jane.doe");
        mockTrainersMap.put(1L, trainer);
        Trainer foundTrainer = trainerDAO.findByUsername("jane.doe");
        assertNotNull(foundTrainer);
        assertEquals(trainer, foundTrainer);
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void findByUsername_ShouldReturnNull_WhenTrainerDoesNotExist() {
        Trainer foundTrainer = trainerDAO.findByUsername("nonexistent.user");
        assertNull(foundTrainer);
        verify(storage, times(1)).getEntities("trainers");
    }
}
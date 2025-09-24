package com.gymcrm.dao;

import com.gymcrm.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainerDAOTest extends AbstractDAOTest<TrainerDAO, Trainer> {

    @InjectMocks
    private TrainerDAO trainerDAO;

    @Override
    protected String getNamespace() {
        return "trainers";
    }

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        // Специальная логика setUp, если она нужна для TrainerDAO
    }

    @Test
    void create_ShouldGenerateIdAndSaveTrainer() {
        Trainer trainer = new Trainer();
        trainerDAO.create(trainer);
        assertNotNull(trainer.getUserId());
        assertFalse(mockEntitiesMap.isEmpty());
        assertEquals(1, mockEntitiesMap.size());
        assertEquals(trainer, mockEntitiesMap.get(trainer.getUserId()));
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void findById_ShouldReturnTrainer_WhenTrainerExists() {
        Trainer existingTrainer = new Trainer();
        existingTrainer.setUserId(1L);
        mockEntitiesMap.put(1L, existingTrainer);
        Trainer foundTrainer = trainerDAO.findById(1L);
        assertNotNull(foundTrainer);
        assertEquals(existingTrainer, foundTrainer);
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
        originalTrainer.setFirstName("Jane");
        mockEntitiesMap.put(1L, originalTrainer);

        Trainer updatedTrainer = new Trainer();
        updatedTrainer.setUserId(1L);
        updatedTrainer.setFirstName("John");

        Trainer result = trainerDAO.update(updatedTrainer);
        assertNotNull(result);
        assertEquals("John", ((Trainer) mockEntitiesMap.get(1L)).getFirstName());
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
        mockEntitiesMap.put(1L, trainer);
        trainerDAO.delete(1L);
        assertFalse(mockEntitiesMap.containsKey(1L));
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void delete_ShouldDoNothing_WhenTrainerDoesNotExist() {
        trainerDAO.delete(99L);
        assertTrue(mockEntitiesMap.isEmpty());
        verify(storage, times(1)).getEntities("trainers");
    }

    @Test
    void findByUsername_ShouldReturnTrainer_WhenTrainerExists() {
        Trainer trainer = new Trainer();
        trainer.setUsername("jane.doe");
        mockEntitiesMap.put(1L, trainer);
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
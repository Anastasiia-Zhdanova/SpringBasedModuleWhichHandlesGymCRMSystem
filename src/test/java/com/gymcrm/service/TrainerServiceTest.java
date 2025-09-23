package com.gymcrm.service;

import com.gymcrm.dao.TrainerDAO;
import com.gymcrm.model.Trainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainerServiceTest {

    @Mock
    private TrainerDAO trainerDAO;

    @InjectMocks
    private TrainerService trainerService;

    @Test
    void createTrainer_ShouldGenerateUsernameAndPassword() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("Jane");
        trainer.setLastName("Doe");

        trainerService.createTrainer(trainer);

        assertNotNull(trainer.getUsername());
        assertNotNull(trainer.getPassword());
        assertEquals("jane.doe", trainer.getUsername());
        assertEquals(10, trainer.getPassword().length());
        verify(trainerDAO, times(1)).create(trainer);
    }

    @Test
    void updateTrainer_ShouldCallDao_AndReturnUpdatedTrainer() {
        Trainer trainer = new Trainer();
        when(trainerDAO.update(trainer)).thenReturn(trainer);
        Trainer updated = trainerService.updateTrainer(trainer);
        verify(trainerDAO, times(1)).update(trainer);
        assertEquals(trainer, updated);
    }

    @Test
    void updateTrainer_ShouldReturnNull_WhenTrainerNotFound() {
        Trainer trainer = new Trainer();
        when(trainerDAO.update(trainer)).thenReturn(null);
        Trainer updated = trainerService.updateTrainer(trainer);
        assertNull(updated);
        verify(trainerDAO, times(1)).update(trainer);
    }

    @Test
    void deleteTrainer_ShouldCallDao() {
        Long trainerId = 1L;
        trainerService.deleteTrainer(trainerId);
        verify(trainerDAO, times(1)).delete(trainerId);
    }

    @Test
    void findTrainerById_ShouldCallDao_AndReturnTrainer() {
        Long trainerId = 1L;
        Trainer expectedTrainer = new Trainer();
        when(trainerDAO.findById(trainerId)).thenReturn(expectedTrainer);
        Trainer foundTrainer = trainerService.findTrainerById(trainerId);
        verify(trainerDAO, times(1)).findById(trainerId);
        assertEquals(expectedTrainer, foundTrainer);
    }

    @Test
    void findTrainerById_ShouldReturnNull_WhenTrainerNotFound() {
        Long trainerId = 1L;
        when(trainerDAO.findById(trainerId)).thenReturn(null);
        Trainer foundTrainer = trainerService.findTrainerById(trainerId);
        assertNull(foundTrainer);
        verify(trainerDAO, times(1)).findById(trainerId);
    }
}
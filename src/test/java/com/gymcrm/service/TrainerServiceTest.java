package com.gymcrm.service;

import com.gymcrm.dao.TrainerDAO;
import com.gymcrm.model.Trainer;
import org.junit.jupiter.api.BeforeEach;
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

    private Trainer trainer;
    private final Long userId = 1L;

    @BeforeEach
    void setUp() {
        trainer = new Trainer();
        trainer.setUserId(userId);
    }

    @Test
    void updateTrainer_ShouldReturnUpdatedTrainer_WhenTrainerExists() {
        when(trainerDAO.update(trainer)).thenReturn(trainer);
        Trainer updated = trainerService.updateTrainer(trainer);
        assertNotNull(updated);
        verify(trainerDAO, times(1)).update(trainer);
    }

    @Test
    void updateTrainer_ShouldReturnNull_WhenTrainerDoesNotExist() {
        when(trainerDAO.update(trainer)).thenReturn(null);
        Trainer updated = trainerService.updateTrainer(trainer);
        assertNull(updated);
        verify(trainerDAO, times(1)).update(trainer);
    }

    @Test
    void deleteTrainer_ShouldCallDeleteInAbstractClass() {
        doNothing().when(trainerDAO).delete(userId);
        trainerService.deleteTrainer(userId);
        verify(trainerDAO, times(1)).delete(userId);
    }

    @Test
    void findTrainerById_ShouldReturnTrainer_WhenTrainerExists() {
        when(trainerDAO.findById(userId)).thenReturn(trainer);
        Trainer found = trainerService.findTrainerById(userId);
        assertNotNull(found);
        assertEquals(userId, found.getUserId());
        verify(trainerDAO, times(1)).findById(userId);
    }

    @Test
    void findTrainerById_ShouldReturnNull_WhenTrainerDoesNotExist() {
        when(trainerDAO.findById(userId)).thenReturn(null);
        Trainer found = trainerService.findTrainerById(userId);
        assertNull(found);
        verify(trainerDAO, times(1)).findById(userId);
    }
}
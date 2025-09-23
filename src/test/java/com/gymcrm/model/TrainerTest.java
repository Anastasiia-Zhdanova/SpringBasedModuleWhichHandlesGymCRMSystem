package com.gymcrm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainerTest {

    @Test
    public void testTrainerGettersAndSetters() {
        Trainer trainer = new Trainer();
        long userId = 456L;

        trainer.setFirstName("Jane");
        trainer.setLastName("Doe");
        trainer.setSpecialization("Yoga");
        trainer.setUserId(userId);

        assertEquals("Jane", trainer.getFirstName());
        assertEquals("Doe", trainer.getLastName());
        assertEquals("Yoga", trainer.getSpecialization());
        assertEquals(userId, trainer.getUserId());
    }
}
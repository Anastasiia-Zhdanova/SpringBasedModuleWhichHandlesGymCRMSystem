package com.gymcrm.model;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TraineeTest {

    @Test
    public void testTraineeGettersAndSetters() {
        Trainee trainee = new Trainee();
        Date dateOfBirth = new Date();
        long userId = 123L;

        trainee.setFirstName("John");
        trainee.setLastName("Doe");
        trainee.setAddress("123 Main Street");
        trainee.setDateOfBirth(dateOfBirth);
        trainee.setUserId(userId);

        assertEquals("John", trainee.getFirstName());
        assertEquals("Doe", trainee.getLastName());
        assertEquals("123 Main Street", trainee.getAddress());
        assertEquals(dateOfBirth, trainee.getDateOfBirth());
        assertEquals(userId, trainee.getUserId());
        assertNotNull(trainee.getDateOfBirth());
    }
}
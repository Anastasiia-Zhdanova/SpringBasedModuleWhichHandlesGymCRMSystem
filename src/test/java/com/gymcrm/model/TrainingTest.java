package com.gymcrm.model;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TrainingTest {

    @Test
    public void testTrainingGettersAndSetters() {
        Training training = new Training();
        TrainingType trainingType = new TrainingType();
        trainingType.setTrainingType("Cardio");
        Date trainingDate = new Date();
        long traineeId = 1L;
        long trainerId = 2L;
        String trainingName = "Morning Run";
        int trainingDuration = 60;

        training.setTraineeId(traineeId);
        training.setTrainerId(trainerId);
        training.setTrainingName(trainingName);
        training.setTrainingType(trainingType);
        training.setTrainingDate(trainingDate);
        training.setTrainingDuration(trainingDuration);

        assertEquals(traineeId, training.getTraineeId());
        assertEquals(trainerId, training.getTrainerId());
        assertEquals(trainingName, training.getTrainingName());
        assertEquals(trainingType, training.getTrainingType());
        assertEquals(trainingDate, training.getTrainingDate());
        assertEquals(trainingDuration, training.getTrainingDuration());
        assertNotNull(training.getTrainingType());
        assertEquals("Cardio", training.getTrainingType().getTrainingType());
    }
}
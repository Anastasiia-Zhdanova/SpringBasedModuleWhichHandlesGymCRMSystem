package com.gymcrm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingTypeTest {

    @Test
    public void testTrainingTypeGettersAndSetters() {
        TrainingType trainingType = new TrainingType();
        String typeName = "Cardio";
        trainingType.setTrainingType(typeName);
        assertEquals(typeName, trainingType.getTrainingType());
    }
}
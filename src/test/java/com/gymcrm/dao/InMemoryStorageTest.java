package com.gymcrm.dao;

import com.gymcrm.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource("classpath:application.properties")
public class InMemoryStorageTest {

    @Autowired
    private InMemoryStorage storage;

    @Test
    void storageShouldBeInitializedFromPropertiesFile() {
        assertNotNull(storage);
        Map<Long, Object> trainersMap = storage.getEntities("trainers");
        Map<Long, Object> traineesMap = storage.getEntities("trainees");
        assertFalse(trainersMap.isEmpty(), "Trainers map should not be empty after initialization.");
        assertFalse(traineesMap.isEmpty(), "Trainees map should not be empty after initialization.");
        assertEquals(1, trainersMap.size());
        assertEquals(1, traineesMap.size());
    }
}
package com.gymcrm.dao;

import com.gymcrm.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InMemoryStorageErrorTest {

    @Test
    void initialize_ShouldThrowRuntimeException_WhenFileIsNotFound() {
        assertThrows(BeanCreationException.class, () -> {
            try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext()) {
                context.getEnvironment().getSystemProperties().put("storage.initial.data.path", "non-existent-file.json");
                context.register(AppConfig.class);
                context.refresh();
            }
        });
    }
}
package com.gymcrm.config;

import com.gymcrm.facade.GymFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestPropertySource("classpath:application.properties")
public class AppConfigTest {

    @Autowired
    private GymFacade gymFacade;

    @Test
    public void testGymFacadeBeanIsCreated() {
        assertNotNull(gymFacade);
    }
}
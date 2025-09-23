package com.gymcrm.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = PropertyConfig.class)
@TestPropertySource("classpath:application.properties")
public class PropertyConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${test.property}")
    private String testPropertyValue;

    @Test
    void propertyConfig_ShouldBeLoadedBySpringContext() {
        assertNotNull(applicationContext);
    }

    @Test
    void testPropertySource_ShouldOverrideProperties() {
        assertEquals("test-value", testPropertyValue);
    }
}
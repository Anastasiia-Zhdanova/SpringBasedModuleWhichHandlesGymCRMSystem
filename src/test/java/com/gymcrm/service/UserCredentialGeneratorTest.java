package com.gymcrm.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCredentialGeneratorTest {

    @Test
    void generatePassword_ShouldReturnTenCharString() {
        String password = UserCredentialGenerator.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}
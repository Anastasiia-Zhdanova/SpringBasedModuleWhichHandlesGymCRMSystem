package com.gymcrm.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserCredentialGeneratorTest {

    @Test
    void generateUserName_ShouldConcatenateFirstAndLastName_WithDotSeparator() {
        String username = UserCredentialGenerator.generateUserName("John", "Doe");
        assertEquals("john.doe", username);
    }

    @Test
    void generateUserName_ShouldReturnFirstName_WhenLastNameIsEmpty() {
        String username = UserCredentialGenerator.generateUserName("John", "");
        assertEquals("john", username);
    }

    @Test
    void generateUserName_ShouldReturnLastName_WhenFirstNameIsEmpty() {
        String username = UserCredentialGenerator.generateUserName("", "Doe");
        assertEquals("doe", username);
    }

    @Test
    void generateUserName_ShouldReturnEmptyString_WhenBothNamesAreEmpty() {
        String username = UserCredentialGenerator.generateUserName("", "");
        assertEquals("", username);
    }

    @Test
    void generateUserName_ShouldHandleNullNamesGracefully() {
        String username1 = UserCredentialGenerator.generateUserName(null, "Doe");
        String username2 = UserCredentialGenerator.generateUserName("John", null);
        String username3 = UserCredentialGenerator.generateUserName(null, null);

        assertEquals("doe", username1);
        assertEquals("john", username2);
        assertEquals("", username3);
    }

    @Test
    void generatePassword_ShouldReturn10CharacterString() {
        String password = UserCredentialGenerator.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
    }
}
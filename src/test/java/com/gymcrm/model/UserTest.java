package com.gymcrm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        String firstName = "Test";
        String lastName = "User";
        String username = "test.user";
        String password = "password123";
        boolean isActive = true;

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(password);
        user.setIsActive(isActive);

        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertTrue(user.isActive());
    }

    @Test
    public void testIsActiveWhenFalse() {
        User user = new User();
        user.setIsActive(false);
        assertFalse(user.isActive());
    }

    @Test
    public void testSettersWithNullValues() {
        User user = new User();
        user.setFirstName(null);
        user.setLastName(null);
        user.setUsername(null);
        user.setPassword(null);

        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
    }
}
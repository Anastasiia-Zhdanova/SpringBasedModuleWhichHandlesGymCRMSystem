package com.gymcrm.service;

import com.gymcrm.model.User;
import java.util.Objects;
import java.util.UUID;

public final class UserCredentialGenerator {

    private UserCredentialGenerator() {

    }

    public static void generateAndSetCredentials(User user) {
        user.setUsername(generateUserName(user.getFirstName(), user.getLastName()));
        user.setPassword(generatePassword());
    }

    public static String generateUserName(String firstName, String lastName) {
        String first = Objects.toString(firstName, "");
        String last = Objects.toString(lastName, "");
        if (first.isEmpty() && last.isEmpty()) {
            return "";
        }
        if (first.isEmpty()) {
            return last.toLowerCase();
        }
        if (last.isEmpty()) {
            return first.toLowerCase();
        }
        return (first + "." + last).toLowerCase();
    }

    public static String generatePassword() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
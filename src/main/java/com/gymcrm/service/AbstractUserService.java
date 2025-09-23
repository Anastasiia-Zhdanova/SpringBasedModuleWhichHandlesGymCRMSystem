package com.gymcrm.service;

import com.gymcrm.dao.UserDAO;
import com.gymcrm.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public abstract class AbstractUserService<T extends User> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractUserService.class);

    private UserDAO<T> userDAO;

    @Autowired
    public void setUserDAO(UserDAO<T> userDAO) {
        this.userDAO = userDAO;
    }

    public void create(T user) {
        logger.info("Creating a new user with first name: {}", user.getFirstName());

        // Generate a unique username
        String baseUsername = generateUserName(user.getFirstName(), user.getLastName());
        String uniqueUsername = baseUsername;
        int counter = 0;

        while (userDAO.findByUsername(uniqueUsername) != null) {
            counter++;
            uniqueUsername = baseUsername + counter;
        }

        user.setUsername(uniqueUsername);
        user.setPassword(UserCredentialGenerator.generatePassword());

        userDAO.create(user);
        logger.info("User created with username: {}", user.getUsername());
    }

    public T update(T user) {
        logger.info("Updating user with ID: {}", user.getUserId());
        T updated = userDAO.update(user);
        if (updated != null) {
            logger.info("User with ID {} updated successfully.", user.getUserId());
        } else {
            logger.error("Failed to update user with ID: {}. User not found.", user.getUserId());
        }
        return updated;
    }

    public void delete(Long id) {
        logger.info("Deleting user with ID: {}", id);
        userDAO.delete(id);
        logger.info("User with ID {} deleted.", id);
    }

    public T findById(Long id) {
        logger.info("Finding user by ID: {}", id);
        T user = userDAO.findById(id);
        if (user != null) {
            logger.info("Found user with ID: {}", id);
        } else {
            logger.warn("User with ID {} not found.", id);
        }
        return user;
    }

    private String generateUserName(String firstName, String lastName) {
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
}
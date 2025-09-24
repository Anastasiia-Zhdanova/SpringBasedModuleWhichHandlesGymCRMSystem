package com.gymcrm.service;

import com.gymcrm.dao.UserDAO;
import com.gymcrm.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractUserServiceTest<T extends User, S extends AbstractUserService<T>, D extends UserDAO<T>> {

    protected D userDAO;
    protected S userService;
    protected T user;
    protected final Long userId = 1L;

    protected abstract void setupMocks();

    protected abstract T createNewUserInstance();

    protected abstract T callSpecificUpdate(T user);

    protected abstract void callSpecificDelete(Long userId);

    protected abstract T callSpecificFindById(Long userId);

    @BeforeEach
    void setUp() {
        setupMocks();

        user = createNewUserInstance();
        user.setUserId(userId);
    }

    @Test
    void create_ShouldGenerateUniqueUsernameAndSetPassword() {
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userDAO.findByUsername("john.doe")).thenReturn(null);
        when(userDAO.create(user)).thenReturn(user);

        userService.create(user);

        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertEquals("john.doe", user.getUsername());
        assertEquals(10, user.getPassword().length());
        verify(userDAO, times(1)).create(user);
    }

    @Test
    void create_ShouldGenerateUniqueUsernameWhenCollision() {
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userDAO.findByUsername("john.doe")).thenReturn(createNewUserInstance());
        when(userDAO.findByUsername("john.doe1")).thenReturn(null);
        when(userDAO.create(user)).thenReturn(user);

        userService.create(user);

        assertEquals("john.doe1", user.getUsername());
        verify(userDAO, times(1)).create(user);
        verify(userDAO, times(1)).findByUsername("john.doe");
        verify(userDAO, times(1)).findByUsername("john.doe1");
    }

    @Test
    void generateUserName_ShouldHandleEmptyFirstName() {
        user.setFirstName("");
        user.setLastName("Smith");

        when(userDAO.findByUsername("smith")).thenReturn(null);
        when(userDAO.create(user)).thenReturn(user);

        userService.create(user);

        assertEquals("smith", user.getUsername());
        verify(userDAO, times(1)).create(user);
    }

    @Test
    void generateUserName_ShouldHandleEmptyLastName() {
        user.setFirstName("Alex");
        user.setLastName("");

        when(userDAO.findByUsername("alex")).thenReturn(null);
        when(userDAO.create(user)).thenReturn(user);

        userService.create(user);

        assertEquals("alex", user.getUsername());
        verify(userDAO, times(1)).create(user);
    }

    @Test
    void generateUserName_ShouldHandleBothEmpty() {
        user.setFirstName(null);
        user.setLastName(null);

        when(userDAO.findByUsername("")).thenReturn(null);
        when(userDAO.create(user)).thenReturn(user);

        userService.create(user);

        assertEquals("", user.getUsername());
        verify(userDAO, times(1)).create(user);
        verify(userDAO, times(1)).findByUsername("");
    }

    @Test
    void specificUpdate_ShouldReturnUpdatedUser_WhenUserExists() {
        when(userDAO.update(user)).thenReturn(user);
        T updated = callSpecificUpdate(user);

        assertNotNull(updated);
        verify(userDAO, times(1)).update(user);
        assertEquals(userId, updated.getUserId());
    }

    @Test
    void specificUpdate_ShouldReturnNull_WhenUserDoesNotExist() {
        when(userDAO.update(user)).thenReturn(null);
        T updated = callSpecificUpdate(user);

        assertNull(updated);
        verify(userDAO, times(1)).update(user);
    }

    @Test
    void specificDelete_ShouldCallDaoDeleteMethod() {
        doNothing().when(userDAO).delete(userId);
        callSpecificDelete(userId);

        verify(userDAO, times(1)).delete(userId);
    }

    @Test
    void specificFindById_ShouldReturnUser_WhenUserExists() {
        when(userDAO.findById(userId)).thenReturn(user);
        T found = callSpecificFindById(userId);

        assertNotNull(found);
        assertEquals(userId, found.getUserId());
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    void specificFindById_ShouldReturnNull_WhenUserDoesNotExist() {
        when(userDAO.findById(userId)).thenReturn(null);
        T found = callSpecificFindById(userId);

        assertNull(found);
        verify(userDAO, times(1)).findById(userId);
    }
}
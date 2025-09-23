package com.gymcrm.service;

import com.gymcrm.dao.UserDAO;
import com.gymcrm.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AbstractUserServiceTest {

    private static class ConcreteUserService extends AbstractUserService<User> {
        public ConcreteUserService(UserDAO<User> userDAO) {
            super();
            this.setUserDAO(userDAO);
        }
    }

    @Mock
    private UserDAO<User> userDAO;

    @InjectMocks
    private ConcreteUserService abstractUserService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void create_ShouldGenerateUniqueUsernameAndSetPassword() {
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userDAO.findByUsername("john.doe")).thenReturn(null);
        when(userDAO.create(any(User.class))).thenReturn(user);

        abstractUserService.create(user);

        assertNotNull(user.getUsername());
        assertNotNull(user.getPassword());
        assertEquals("john.doe", user.getUsername());
        assertEquals(10, user.getPassword().length());
        verify(userDAO, times(1)).create(user);
    }

    @Test
    void create_ShouldGenerateUniqueUsername_WithCounter_WhenUsernameExists() {
        user.setFirstName("Jane");
        user.setLastName("Doe");
        User existingUser = new User();

        when(userDAO.findByUsername("jane.doe")).thenReturn(existingUser);
        when(userDAO.findByUsername("jane.doe1")).thenReturn(null);

        abstractUserService.create(user);

        assertNotNull(user.getUsername());
        assertEquals("jane.doe1", user.getUsername());
        verify(userDAO, times(2)).findByUsername(anyString());
        verify(userDAO, times(1)).create(user);
    }

    @Test
    void create_ShouldGenerateUsername_WithOnlyFirstName() {
        user.setFirstName("Mike");
        user.setLastName(null);

        when(userDAO.findByUsername("mike")).thenReturn(null);
        when(userDAO.create(any(User.class))).thenReturn(user);

        abstractUserService.create(user);

        assertEquals("mike", user.getUsername());
    }

    @Test
    void create_ShouldGenerateUsername_WithOnlyLastName() {
        user.setFirstName(null);
        user.setLastName("Smith");

        when(userDAO.findByUsername("smith")).thenReturn(null);
        when(userDAO.create(any(User.class))).thenReturn(user);

        abstractUserService.create(user);

        assertEquals("smith", user.getUsername());
    }

    @Test
    void create_ShouldGenerateUsername_WithEmptyStrings() {
        user.setFirstName("");
        user.setLastName("");

        when(userDAO.findByUsername("")).thenReturn(null);
        when(userDAO.create(any(User.class))).thenReturn(user);

        abstractUserService.create(user);

        assertEquals("", user.getUsername());
    }

    @Test
    void create_ShouldGenerateUsername_WithNullValues() {
        user.setFirstName(null);
        user.setLastName(null);

        when(userDAO.findByUsername("")).thenReturn(null);
        when(userDAO.create(any(User.class))).thenReturn(user);

        abstractUserService.create(user);

        assertEquals("", user.getUsername());
    }


    @Test
    void update_ShouldReturnUpdatedUser_WhenUserExists() {
        user.setUserId(1L);
        when(userDAO.update(user)).thenReturn(user);

        User updatedUser = abstractUserService.update(user);

        assertNotNull(updatedUser);
        verify(userDAO, times(1)).update(user);
    }

    @Test
    void update_ShouldReturnNull_WhenUserDoesNotExist() {
        user.setUserId(99L);
        when(userDAO.update(user)).thenReturn(null);

        User updatedUser = abstractUserService.update(user);

        assertNull(updatedUser);
        verify(userDAO, times(1)).update(user);
    }

    @Test
    void delete_ShouldCallDaoDeleteMethod() {
        Long userId = 1L;
        doNothing().when(userDAO).delete(userId);

        abstractUserService.delete(userId);

        verify(userDAO, times(1)).delete(userId);
    }

    @Test
    void findById_ShouldReturnUser_WhenUserExists() {
        Long userId = 1L;
        when(userDAO.findById(userId)).thenReturn(user);

        User foundUser = abstractUserService.findById(userId);

        assertNotNull(foundUser);
        verify(userDAO, times(1)).findById(userId);
    }

    @Test
    void findById_ShouldReturnNull_WhenUserDoesNotExist() {
        Long userId = 99L;
        when(userDAO.findById(userId)).thenReturn(null);

        User foundUser = abstractUserService.findById(userId);

        assertNull(foundUser);
        verify(userDAO, times(1)).findById(userId);
    }
}
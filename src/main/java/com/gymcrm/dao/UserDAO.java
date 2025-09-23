package com.gymcrm.dao;

import com.gymcrm.model.User;

public interface UserDAO<T extends User> {
    T create(T user);
    T update(T user);
    void delete(Long id);
    T findById(Long id);
    T findByUsername(String username);
}
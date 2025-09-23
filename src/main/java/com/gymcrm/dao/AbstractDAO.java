package com.gymcrm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

public abstract class AbstractDAO<T> {

    @Autowired
    protected InMemoryStorage storage;

    protected abstract String getNamespace();
    protected abstract Long getNextId();
    protected abstract Long getId(T entity);
    protected abstract void setId(T entity, Long id);

    public T create(T entity) {
        Map<Long, Object> entitiesMap = storage.getEntities(getNamespace());
        setId(entity, getNextId());
        entitiesMap.put(getId(entity), entity);
        return entity;
    }

    public T findById(Long id) {
        Map<Long, Object> entitiesMap = storage.getEntities(getNamespace());
        return (T) entitiesMap.get(id);
    }

    public T update(T entity) {
        Map<Long, Object> entitiesMap = storage.getEntities(getNamespace());
        if (entitiesMap.containsKey(getId(entity))) {
            entitiesMap.put(getId(entity), entity);
            return entity;
        }
        return null;
    }

    public void delete(Long id) {
        Map<Long, Object> entitiesMap = storage.getEntities(getNamespace());
        entitiesMap.remove(id);
    }
}
package com.gymcrm.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public abstract class AbstractDAOTest<T extends AbstractDAO, E> {

    @Mock
    protected InMemoryStorage storage;

    protected Map<Long, Object> mockEntitiesMap;

    @BeforeEach
    public void setUp() {
        mockEntitiesMap = new HashMap<>();
        when(storage.getEntities(getNamespace())).thenReturn(mockEntitiesMap);
    }

    protected abstract String getNamespace();
}
package com.gymcrm.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("storage")
public class InMemoryStorage {

    private final ResourceLoader resourceLoader;

    @Value("${storage.initial.data.path}")
    private String initialDataPath;

    private final Map<String, Map<Long, Object>> storage = new HashMap<>();

    public InMemoryStorage(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Map<Long, Object> getEntities(String namespace) {
        return storage.computeIfAbsent(namespace, k -> new HashMap<>());
    }

    @PostConstruct
    public void initialize() {
        try {
            Resource resource = resourceLoader.getResource("classpath:" + initialDataPath);
            try (InputStream inputStream = resource.getInputStream()) {
                ObjectMapper mapper = new ObjectMapper();

                mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

                Map<String, List<Map<String, Object>>> initialData = mapper.readValue(inputStream, Map.class);

                for (Map.Entry<String, List<Map<String, Object>>> entry : initialData.entrySet()) {
                    String namespace = entry.getKey();
                    Map<Long, Object> entitiesMap = getEntities(namespace);
                    for (Map<String, Object> entityData : entry.getValue()) {
                        Long id = ((Number) entityData.get("userId")).longValue();
                        entitiesMap.put(id, entityData);
                    }
                }
                System.out.println("Storage initialized from " + initialDataPath);
            }
        } catch (Exception e) {
            System.err.println("Failed to initialize storage: " + e.getMessage());
            throw new RuntimeException("Failed to initialize storage", e);
        }
    }
}
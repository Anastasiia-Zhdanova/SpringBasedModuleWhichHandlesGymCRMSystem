package com.gymcrm.service;

import com.gymcrm.dao.TrainingDAO;
import com.gymcrm.model.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);

    private TrainingDAO trainingDAO;

    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    public void createTraining(Training training) {
        logger.info("Creating a new Training with name: {}", training.getTrainingName());
        trainingDAO.create(training);
        logger.info("Training created with ID: {}", training.getId());
    }

    public Training selectTraining(long id) {
        logger.info("Finding Training by ID: {}", id);
        Training training = trainingDAO.findById(id);
        if (training != null) {
            logger.info("Found Training with ID: {}", id);
        } else {
            logger.warn("Training with ID {} not found.", id);
        }
        return training;
    }
}
package com.gymcrm.service;

import com.gymcrm.dao.TrainerDAO;
import com.gymcrm.model.Trainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TrainerService extends AbstractUserService<Trainer> {

    private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);

    // Dependency injection
    public TrainerService(TrainerDAO trainerDAO) {
        super(trainerDAO);
    }

    public void createTrainer(Trainer trainer) {
        logger.info("Creating a new Trainer with first name: {}", trainer.getFirstName());
        UserCredentialGenerator.generateAndSetCredentials(trainer);
        create(trainer);
        logger.info("Trainer created with username: {}", trainer.getUsername());
    }

    public Trainer updateTrainer(Trainer trainer) {
        logger.info("Updating Trainer with ID: {}", trainer.getUserId());
        Trainer updated = update(trainer);
        if (updated != null) {
            logger.info("Trainer with ID {} updated successfully.", trainer.getUserId());
        } else {
            logger.error("Failed to update Trainer with ID: {}. Trainer not found.", trainer.getUserId());
        }
        return updated;
    }

    public void deleteTrainer(Long trainerId) {
        logger.info("Deleting Trainer with ID: {}", trainerId);
        delete(trainerId);
        logger.info("Trainer with ID {} deleted.", trainerId);
    }

    public Trainer findTrainerById(Long trainerId) {
        logger.info("Finding Trainer by ID: {}", trainerId);
        Trainer trainer = findById(trainerId);
        if (trainer != null) {
            logger.info("Found Trainer with ID: {}", trainerId);
        } else {
            logger.warn("Trainer with ID {} not found.", trainerId);
        }
        return trainer;
    }
}
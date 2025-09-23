package com.gymcrm.service;

import com.gymcrm.dao.TraineeDAO;
import com.gymcrm.model.Trainee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TraineeService extends AbstractUserService<Trainee> {

    private static final Logger logger = LoggerFactory.getLogger(TraineeService.class);

    public TraineeService(TraineeDAO traineeDAO) {
        super();
        this.setUserDAO(traineeDAO);
    }

    public Trainee updateTrainee(Trainee trainee) {
        logger.info("Updating Trainee with ID: {}", trainee.getUserId());
        Trainee updated = update(trainee);
        if (updated != null) {
            logger.info("Trainee with ID {} updated successfully.", trainee.getUserId());
        } else {
            logger.error("Failed to update Trainee with ID: {}", trainee.getUserId());
        }
        return updated;
    }

    public void deleteTrainee(Long id) {
        logger.info("Deleting Trainee with ID: {}", id);
        delete(id);
        logger.info("Trainee with ID {} deleted.", id);
    }

    public Trainee findTraineeById(Long id) {
        logger.info("Finding Trainee by ID: {}", id);
        Trainee trainee = findById(id);
        if (trainee != null) {
            logger.info("Found Trainee with ID: {}", id);
        } else {
            logger.warn("Trainee with ID {} not found.", id);
        }
        return trainee;
    }
}
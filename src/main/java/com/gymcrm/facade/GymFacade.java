package com.gymcrm.facade;

import com.gymcrm.model.Trainee;
import com.gymcrm.model.Trainer;
import com.gymcrm.model.Training;
import com.gymcrm.service.TraineeService;
import com.gymcrm.service.TrainerService;
import com.gymcrm.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GymFacade {

    private final TraineeService traineeService;
    private final TrainerService trainerService;
    private final TrainingService trainingService;

    // Dependency injections
    @Autowired
    public GymFacade(TraineeService traineeService, TrainerService trainerService, TrainingService trainingService) {
        this.traineeService = traineeService;
        this.trainerService = trainerService;
        this.trainingService = trainingService;
    }

    public void createTrainee(Trainee trainee) {
        traineeService.create(trainee);
    }

    public Trainee updateTrainee(Trainee trainee) {
        return traineeService.update(trainee);
    }

    public void deleteTrainee(Long id) {
        traineeService.delete(id);
    }

    public Trainee findTraineeById(Long id) {
        return traineeService.findById(id);
    }

    public void createTrainer(Trainer trainer) {
        trainerService.create(trainer);
    }

    public Trainer updateTrainer(Trainer trainer) {
        return trainerService.update(trainer);
    }

    public void deleteTrainer(Long trainerId) {
        trainerService.delete(trainerId);
    }

    public Trainer findTrainerById(Long trainerId) {
        return trainerService.findById(trainerId);
    }

    public void createTraining(Training training) {
        trainingService.createTraining(training);
    }

    public Training selectTraining(long id) {
        return trainingService.selectTraining(id);
    }
}
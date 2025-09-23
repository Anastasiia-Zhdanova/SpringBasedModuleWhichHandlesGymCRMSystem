# SpringBasedModuleWhichHandlesGymCRMSystem
Spring Based Module Which Handles Gym CRM System

# Project Structure

The project is structured into several packages, each with a specific responsibility within the application's architecture:

---

### `com.gymcrm.config`

This package contains the classes for Spring context configuration.

* **`AppConfig.java`**: The main configuration class that enables component scanning (`@ComponentScan`) and imports property configurations.
* **`PropertyConfig.java`**: A class responsible for loading external property files, such as `application.properties`.

---

### `com.gymcrm.dao`

This package contains the Data Access Objects (DAOs) that handle interactions with the in-memory storage.

* **`InMemoryStorage.java`**: The central class that acts as the in-memory data store. It uses a `Map<String, Map<Long, Object>>` to organize data by namespace (`trainees`, `trainers`, `trainings`) and is responsible for initializing data from `initial-data.json`.
* **`AbstractDAO.java`**: An abstract base class for all DAOs, providing common logic for creating, finding, updating, and deleting entities.
* **`TraineeDAO.java`**, **`TrainerDAO.java`**, **`TrainingDAO.java`**: Concrete DAO implementations for their respective entity types.

---

### `com.gymcrm.model`

This package holds the domain model classes.

* **`User.java`**, **`Trainee.java`**, **`Trainer.java`**, **`Training.java`**, **`TrainingType.java`**: These classes represent the core entities of the gym CRM system.

---

### `com.gymcrm.service`

This package contains the service classes that implement the business logic.

* **`AbstractUserService.java`**: An abstract base class for user-related services (`TraineeService` and `TrainerService`), providing shared methods like `create`, `update`, `delete`, and `findById`.
* **`TraineeService.java`**: The service for managing `Trainee` entities.
* **`TrainerService.java`**: The service for managing `Trainer` entities.
* **`TrainingService.java`**: The service for managing `Training` entities.
* **`UserCredentialGenerator.java`**: A utility class with static methods for generating usernames and passwords.

---

### `com.gymcrm.facade`

This package likely contains a facade class (`GymFacade.java`) that acts as a single entry point for client interactions, simplifying the interface to the business logic layer.

---

### `resources`

This directory contains configuration files.

* **`application.properties`**: A file storing external properties, such as the path to the initial data file.
* **`initial-data.json`**: The file with initial data for the in-memory storage.

---

### Tests

Test classes are located in the `src/test/java` directory, mirroring the main package structure (e.g., `com.gymcrm.service.TraineeServiceTest`). This structure facilitates unit testing of each component.

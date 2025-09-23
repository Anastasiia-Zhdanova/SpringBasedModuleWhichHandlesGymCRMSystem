package com.gymcrm;

import com.gymcrm.dao.*;
import com.gymcrm.service.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.gymcrm.facade.GymFacadeTest;
import com.gymcrm.model.TraineeTest;
import com.gymcrm.model.TrainerTest;
import com.gymcrm.model.TrainingTest;
import com.gymcrm.model.TrainingTypeTest;
import com.gymcrm.model.UserTest;
import com.gymcrm.config.AppConfigTest;
import com.gymcrm.config.PropertyConfigTest;

@Suite
@SelectClasses({
        TraineeDAOTest.class,
        TrainerDAOTest.class,
        TrainingDAOTest.class,
        GymFacadeTest.class,
        TraineeServiceTest.class,
        TrainerServiceTest.class,
        TrainingServiceTest.class,
        TraineeTest.class,
        TrainerTest.class,
        TrainingTest.class,
        TrainingTypeTest.class,
        UserTest.class,
        AppConfigTest.class,
        PropertyConfigTest.class,
        InMemoryStorageTest.class,
        InMemoryStorageErrorTest.class,
        UserCredentialGeneratorTest.class,
        AbstractUserServiceTest.class
})
public class AllTestsRun {

}
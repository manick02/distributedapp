package com.mshan;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppStateModelFactoryTest {

    @Test
    public void testCreation() {
        AppStateModelFactory factory = new AppStateModelFactory();
        AppStateModel newStateModel = factory.createNewStateModel("resourceName", "partitionName");
        Assertions.assertNotNull(newStateModel);
    }

}
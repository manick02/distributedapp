package com.mshan;

import org.apache.helix.participant.statemachine.StateModelFactory;

public class AppStateModelFactory extends StateModelFactory<AppStateModel> {
    @Override
    public AppStateModel createNewStateModel(String resourceName, String partitionName) {
        return new AppStateModel(partitionName);
    }

}

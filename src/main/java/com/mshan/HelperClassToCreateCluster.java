package com.mshan;

import org.apache.helix.HelixManager;
import org.apache.helix.controller.HelixControllerMain;
import org.apache.helix.examples.OnlineOfflineStateModelFactory;
import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.IdealState;
import org.apache.helix.model.StateModelDefinition;
import org.apache.helix.tools.StateModelConfigGenerator;

import java.util.concurrent.*;

public class HelperClassToCreateCluster {
    public static final String CONTROLLER_NAME = "controller";
    static final String ZK_ADDRESS = "localhost:2181";
    static String CLUSTER_NAME = "demo";
    static int NUMBEROFPARTICIPANT = 10;
    static final ExecutorService executorService = new ThreadPoolExecutor(NUMBEROFPARTICIPANT,2*NUMBEROFPARTICIPANT,50, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
    static  ZKHelixAdmin admin = new ZKHelixAdmin(ZK_ADDRESS);
    static HelixManager controllerManager = null;
    static String RESOURCE_NAME="test_resource";
    static int NUM_OF_PARTITION = 6;
    static String ONLINE_OFFLINE="ONLINEOFFLINE";

    public static void main(String[] args){
       SetupCluster();
       SetupController();
       SetupResource();
       SetupParticipant();
       DemoRebalancing();
    }

    private static void DemoRebalancing() {
    }

    private static void SetupParticipant() {
    }

    private static void SetupResource() {
        admin.addResource(CLUSTER_NAME,RESOURCE_NAME,NUM_OF_PARTITION,"ONLINEOFFLINE");
    }

    public static void SetupCluster() {
        admin.addCluster(CLUSTER_NAME,true);
        admin.addStateModelDef(CLUSTER_NAME,ONLINE_OFFLINE, new StateModelDefinition(StateModelConfigGenerator.generateConfigForOnlineOffline()),true);
//        admin.addStateModelDef(CLUSTER_NAME,"ONLINEOFFLINE", new StateModelDefinition());
    }

    public static void SetupController() {
      //  ZKHelixAdmin admin = new ZKHelixAdmin(ZK_ADDRESS);
        controllerManager = HelixControllerMain.startHelixController(ZK_ADDRESS,
                CLUSTER_NAME,
                CONTROLLER_NAME,
                HelixControllerMain.STANDALONE);

    }


}

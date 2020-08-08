package com.mshan;

import org.apache.helix.HelixManager;
import org.apache.helix.HelixManagerFactory;
import org.apache.helix.InstanceType;
import org.apache.helix.controller.HelixControllerMain;
import org.apache.helix.examples.OnlineOfflineStateModelFactory;
import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.IdealState;
import org.apache.helix.model.StateModelDefinition;
import org.apache.helix.participant.StateMachineEngine;
import org.apache.helix.tools.StateModelConfigGenerator;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.*;

public class HelperClassToCreateCluster {
    public static final String CONTROLLER_NAME = "controller";
    static final String ZK_ADDRESS = "localhost:2181";
    static String CLUSTER_NAME = "demo";
    static int NUMBEROFPARTICIPANT = 10;
    static final ExecutorService executorService = new ThreadPoolExecutor(NUMBEROFPARTICIPANT,2*NUMBEROFPARTICIPANT,50, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
    static  ZKHelixAdmin admin = new ZKHelixAdmin(ZK_ADDRESS);
    static HelixManager controllerManager = null;
    static HelixManager participantManager = null;
    static String RESOURCE_NAME="test_resource";
    static int NUM_OF_PARTITION = 6;
    static String ONLINE_OFFLINE="ONLINEOFFLINE";
    static String FIRSTINSTANCE = "localhost:9001";
    static String INSTANCENAME = "localhost";
    static int PORT = 9001;

    public static void main(String[] args) throws Exception {
       SetupCluster();
       SetupController();
      // SetupResource();
       SetupParticipant();
       DemoRebalancing();
    }

    private static void DemoRebalancing() {
//        PriorityQueue<int[]> array = new PriorityQueue<int[]>();
//        Arrays.asList(array);

    }

    private static void SetupParticipant() throws Exception {
        for (int i = 0; i < NUMBEROFPARTICIPANT; i++) {
            ParticipantApp participantApp = new ParticipantApp(CLUSTER_NAME,INSTANCENAME + "_" + i, PORT+i,ZK_ADDRESS);
            participantApp.start();
        }

//        OnlineOfflineStateModelFactory stateModelFactory = new OnlineOfflineStateModelFactory();
//        participantManager = HelixManagerFactory.getZKHelixManager(CLUSTER_NAME,FIRSTINSTANCE, InstanceType.PARTICIPANT,ZK_ADDRESS);
//        StateMachineEngine stateMachineEngine = participantManager.getStateMachineEngine();
//        stateMachineEngine.registerStateModelFactory("ONLINEOFFLINE",stateModelFactory);
//        participantManager.connect();
    }

    private static void SetupResource() {
        admin.addResource(CLUSTER_NAME,RESOURCE_NAME,NUM_OF_PARTITION,"ONLINEOFFLINE", IdealState.RebalanceMode.FULL_AUTO.toString());
        admin.rebalance(CLUSTER_NAME,RESOURCE_NAME,1);
    }

    public static void SetupCluster() {
        admin.addCluster(CLUSTER_NAME,true);
        admin.addStateModelDef(CLUSTER_NAME,ONLINE_OFFLINE, new StateModelDefinition(StateModelConfigGenerator.generateConfigForOnlineOffline()),true);
        SetupResource();
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

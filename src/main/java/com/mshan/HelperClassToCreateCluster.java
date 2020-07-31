package com.mshan;

import org.apache.helix.manager.zk.ZKHelixAdmin;

import java.util.concurrent.*;

public class HelperClassToCreateCluster {
    static String ZK_ADDRESS = "localhost:2181";
    static String CLUSTER_NAME = "demo";
    static int NUMBEROFPARTICIPANT = 10;
    static ExecutorService executorService = new ThreadPoolExecutor(NUMBEROFPARTICIPANT,2*NUMBEROFPARTICIPANT,50, TimeUnit.MINUTES,new LinkedBlockingQueue<>());
    static  ZKHelixAdmin admin = new ZKHelixAdmin(ZK_ADDRESS);
    public static void main(String[] args){
       SetupCluster();
       SetupController();
       SetupResource();
       SetupParticipant();
    }

    private static void SetupParticipant() {
    }

    private static void SetupResource() {

    }

    public static void SetupCluster() {
        admin.addCluster(CLUSTER_NAME);
    }

    public static void SetupController() {
        ZKHelixAdmin admin = new ZKHelixAdmin(ZK_ADDRESS);
    }


}

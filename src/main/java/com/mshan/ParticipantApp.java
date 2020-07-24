package com.mshan;

import org.apache.helix.HelixManager;
import org.apache.helix.HelixManagerFactory;
import org.apache.helix.InstanceType;
import org.apache.helix.examples.OnlineOfflineStateModelFactory;
import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.InstanceConfig;
import org.apache.helix.participant.StateMachineEngine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class ParticipantApp implements Runnable
{
//    public static void main( String[] args ) throws InterruptedException {
//        System.out.println( "Hello World!" );
//        ExecutorService executorService = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.MILLISECONDS,  new LinkedBlockingQueue<Runnable>());
//        for(int i=0; i < 10; i++) {
//            executorService.submit(new App());
//        }
//
//        executorService.awaitTermination(10l,TimeUnit.SECONDS);
//        for(int i=0; i < 10; i++) {
//            executorService.submit(new App());
//        }
//       executorService.awaitTermination(10l,TimeUnit.SECONDS);
//        executorService.shutdown();
//        System.out.println("Shutting down");
//    }

    public static void main(String[] args) throws Exception {
        String zkAddress = "localhost:2181";
        String clusterName = "lock-manager-demo";
        //Give a unique id to each process, most commonly used format hostname_port
        String instanceName = "localhost_12000";
        ZKHelixAdmin helixAdmin = new ZKHelixAdmin(zkAddress);
        //configure the instance and provide some metadata
        InstanceConfig config = new InstanceConfig(instanceName);
        config.setHostName("localhost");
        config.setPort("12000");
        helixAdmin.addInstance(clusterName, config);
        //join the cluster
        HelixManager manager;
        manager = HelixManagerFactory.getZKHelixManager(clusterName,
                instanceName,
                InstanceType.PARTICIPANT,
                zkAddress);
//        AppStateModelFactory factory = new AppStateModelFactory();
//        manager.getStateMachineEngine().registerStateModelFactory("OnlineOffline", factory);
        StateMachineEngine stateMachineEngine = manager.getStateMachineEngine();
        OnlineOfflineStateModelFactory factory = new OnlineOfflineStateModelFactory();
        manager.getStateMachineEngine().registerStateModelFactory("OnlineOffline",factory);

        manager.connect();
        Thread.currentThread().join();
    }

    @Override
    public void run() {
       System.out.println("I am running");
    }
}

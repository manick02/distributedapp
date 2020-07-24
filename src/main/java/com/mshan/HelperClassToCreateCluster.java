package com.mshan;

import org.apache.helix.manager.zk.ZKHelixAdmin;

public class HelperClassToCreateCluster {
    public static void main(String[] args){
        String ZK_ADDRESS = "localhost:2181";
        ZKHelixAdmin admin = new ZKHelixAdmin(ZK_ADDRESS);

        String CLUSTER_NAME = "lock-manager-demo";
//Create cluster namespace in zookeeper
        admin.addCluster(CLUSTER_NAME);
    }
}

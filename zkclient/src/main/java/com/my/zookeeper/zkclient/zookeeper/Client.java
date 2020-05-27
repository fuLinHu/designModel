package com.my.zookeeper.zkclient.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/26 22:20
 * @Version V1.0
 */
public class Client {
    private static String server = "192.168.102.137:2181";
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(server);
        if(!zkClient.exists("/root")){
            zkClient.create("/root","192.168.102.137", CreateMode.PERSISTENT);
            System.out.println("创建出的-----");
        }

    }
}

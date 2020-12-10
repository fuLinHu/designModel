package com.forest.tiger.rabbit.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/2 9:32
 * @Version V1.0
 */
public class ZKClient {
    public static CuratorFramework getClient(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        final String zookeeperConnectionString = "192.168.118.131:2181";
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
        client.start();
        return client;
    }
}

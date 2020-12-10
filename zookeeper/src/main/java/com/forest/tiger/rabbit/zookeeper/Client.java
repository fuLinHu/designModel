package com.forest.tiger.rabbit.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/2 8:40
 * @Version V1.0
 */
@Slf4j
public class Client {
    public static void main(String[] args) throws Exception {
        create("/fulinhu");
    }
    public static  String create(String path){
        CuratorFramework client = ZKClient.getClient();
        String s = null;
        try {
            s = client.create().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("创建得节点:"+s);
        return s;
    }

    public static void getData(String path) throws Exception {
        CuratorFramework client = ZKClient.getClient();
        byte[] bytes = client.getData().forPath(path);
        log.info("get data from node :{} successfully.",new String(bytes));
    }

    public static void testSetData(String path,byte[] data) throws Exception {
        CuratorFramework client = ZKClient.getClient();
        client.setData().forPath(path,data);
        byte[] bytes = client.getData().forPath(path);
        log.info("get data from node {} :{} successfully.", path,new String( bytes));
    }


}

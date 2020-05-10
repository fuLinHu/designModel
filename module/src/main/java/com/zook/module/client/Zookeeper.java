package com.zook.module.zk;

import com.sun.xml.internal.ws.util.Constants;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/10 14:39
 * @Version V1.0
 */
public class Zookeeper {
    public static void createZNode(String zNode) throws Exception {
        String path = "/" + zNode;
        String createPath = zk.create(path, null/*data*/, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(createPath);
    }

    private static ZooKeeper zk;
    private static String hostport = "192.168.102.132:2181";
    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);
    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        zk = new ZooKeeper(hostport, 20000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
                    if (Event.EventType.None == event.getType() && null == event.getPath()) {
                        connectedSemaphore.countDown();
                    } else if (event.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                        try {
                            System.out.println("配置已修改，新值为：" + new String(zk.getData(event.getPath(), true, stat)));
                        } catch (Exception e) {
                        }
                    }
                }

            }
        });
        connectedSemaphore.await();

        //创建持久节点
        //delete("flh");
        doDelZnodeRecursively("/flh");
      /*  String node = createNode("flh/flhchildrent", "fu lin hu ", CreateMode.PERSISTENT);
        System.out.println(node);
        //创建持久序号节点
        node = createNode("fulinhu", "fu lin hu ", CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(node);


        //创建临时节点
        node = createNode("fulinhue", "fu lin hu ", CreateMode.EPHEMERAL);
        System.out.println(node);

        //创建临时序号节点
        node = createNode("fulinhueq", "fu lin hu ", CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(node);*/


        Thread.sleep(Integer.MAX_VALUE);
    }

    public static String createNode(String zNode, String obj, CreateMode createMode) throws KeeperException, InterruptedException {

        String path = "/" + zNode;
        String createPath = null;
        try {
            if (exists(path)) {
                return "节点已经存在啊";
            }
            createPath = zk.create(path, obj.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(createPath);
        return createPath;

    }
    //判断是否有子节点
    public static boolean ifHaveChildrent(String path){
        List<String> children = null;
        try {
            children = zk.getChildren(path, false);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(children!=null&&children.size()>0){
            return true;
        }
        return false;

    }

    //判断节点是否存在
    public static boolean exists(String path) {
        Stat exists = null;
        try {
            exists = zk.exists(path, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("判断节点是否存在：监听：：：" + watchedEvent.toString());
                }
            });
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (exists == null) {
            return false;
        } else {
            return true;
        }
    }

    //删除节点不能递归删除 要写递归方法删除
    private static void doDelZnodeRecursively(String path) {
        try {
            List<String> childList = zk.getChildren(path,false);
            if (CollectionUtils.isEmpty(childList)) {
                zk.delete(path,-1);
            } else {
                for(String childName: childList) {
                    String childPath = path + "/" + childName;
                    List<String> grandChildList = zk.getChildren(childPath,false);
                    if (CollectionUtils.isEmpty(grandChildList)) {
                        zk.delete(childPath,-1);
                    } else {
                        doDelZnodeRecursively(childPath);
                    }
                }
                zk.delete(path,-1);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete node recursively", e);
        }
    }
}

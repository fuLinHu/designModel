package com.my.zookeeper.zkclient.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/13 8:46
 * @Version V1.0
 */
public class LockZookeeper {

    public static void main(String[] args) {
        Tick  tick = new Tick();
        ZookeeperLock zookeeperLock = new ZookeeperLock();
        for(int i=0;i<10;i++){
            new Thread(()->{
                tick.remove(zookeeperLock);

            }).start();
        }
        AtomicInteger i = new AtomicInteger();
        //ZookeeperLock zookeeperLock = new ZookeeperLock();
        /*AtomicInteger i = new AtomicInteger();
        Lock lock = new ReentrantLock();
        for (int j = 0; j <100 ; j++) {
            new Thread(()->{
                //Lock myzooklock = zookeeperLock.lock("myzooklock", 60 * 1000);
                lock.lock();
                try {
                    i.getAndIncrement();
                    System.out.println("加锁操作！！");
                }finally {
                    //zookeeperLock.unlock(myzooklock);
                    lock.unlock();
                    System.out.println("加锁操作！！");
                }
            }).start();
        }
        System.out.println(i.get());*/
    }


}

class Tick{
    private  int total = 1000;
    //Lock lock = new ReentrantLock();

    public void remove(ZookeeperLock zookeeperLock){
        Lock myzooklock =null;
        try {
            //lock.lock();
             myzooklock = zookeeperLock.lock("myzooklock", 60 * 1000);
            if(total>0){
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                total--;
            }
            System.out.println(get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(myzooklock!=null){
                zookeeperLock.unlock(myzooklock);
            }

            //lock.unlock();
        }

    }
    public int get(){
        return total;
    }


}

package com.study.javamodel.juc.objectpool.lock;

import com.study.javamodel.juc.objectpool.Pool;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 20:00
 * @Version V1.0
 */
public class ObjectPoolQue implements Pool<SessionObject> {
    ConcurrentLinkedQueue linkedList = new ConcurrentLinkedQueue();
    ExecutorService executorService = Executors.newFixedThreadPool(100);
    public SessionObject get(){

        long l = System.currentTimeMillis();
        for(;;){
            if(!linkedList.isEmpty()){
                synchronized (this){
                    if(!linkedList.isEmpty()){
                        SessionObject o = (SessionObject)linkedList.poll();
                        long l1 = System.currentTimeMillis();
                        System.out.println("获取一个链接："+(l1-l)+" ms:剩余："+linkedList.size());
                        return o;
                    }else{

                    }
                }
            }else{
                executorService.execute(()->{
                    linkedList.add(new SessionObject());
                });
            }
        }
    }

    public void put(SessionObject sessionObject){
        long l = System.currentTimeMillis();
        if(sessionObject!=null){
            linkedList.add(sessionObject);
        }
        long l1 = System.currentTimeMillis();
        System.out.println("释放一个链接："+(l1-l)+" ms:剩余："+linkedList.size());
    }

    @Override
    public Integer getCount() {
        return linkedList.size();
    }

}

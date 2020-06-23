package com.study.javamodel.juc.objectpool;

import com.study.javamodel.juc.objectpool.lock.ObjectPool;
import com.study.javamodel.juc.objectpool.lock.ObjectPoolLock;
import com.study.javamodel.juc.objectpool.lock.ObjectPoolQue;
import com.study.javamodel.juc.objectpool.lock.SessionObject;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 20:03
 * @Version V1.0
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4000);

        //Pool objectPool = new ObjectPoolLock();
        //Pool objectPool = new ObjectPool();
        Pool objectPool = new ObjectPoolQue();

        for(int i =0;i<400;i++){
            SessionObject sessionObject = new SessionObject();
            objectPool.put(sessionObject);
        }
        long l = System.currentTimeMillis();
        long sum = 0;
        while(sum<120000){
            long end = System.currentTimeMillis();
            sum = end - l;
           // System.out.println("运行了："+sum);
            for (int i = 0; i < 4000; i++) {
                executorService.execute(()->{
                    SessionObject sessionObject = (SessionObject)objectPool.get();
                    log.info("<<<<<==="+sessionObject.find()+"====>>>>>>>>>");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    objectPool.put(sessionObject);
                });
            }
        }


    }
}

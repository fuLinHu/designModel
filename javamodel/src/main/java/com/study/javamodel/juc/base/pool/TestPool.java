package com.study.javamodel.juc.base.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/16 16:20
 * @Version V1.0
 */
@Slf4j
public class TestPool {
    public static void main(String[] args) {
        //corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
        //                Executors.defaultThreadFactory(), defaultHandler
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,1000, TimeUnit.MILLISECONDS,workQueue,Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //throw new RuntimeException("getActiveCount: "+executor.getActiveCount()+"\n getCompletedTaskCount::"+executor.getCompletedTaskCount());
                r.run();
                //log.info("getActiveCount: "+executor.getActiveCount()+"\n getCompletedTaskCount::"+executor.getCompletedTaskCount());

            }
        });
        int i =0;
        while (true){
            //log.info("---"+i++);

            threadPoolExecutor.execute(()->{

                try {
                    throw new RuntimeException("当前线程名称："+Thread.currentThread().getName());
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                    /*try {
                        log.info("当前线程名称："+Thread.currentThread().getName());
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
            });
        }


    }

}

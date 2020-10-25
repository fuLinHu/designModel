package com.study.javamodel.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/20 13:20
 * @Version V1.0
 */
public class TestCountDownLach1 {
    static int sub = 0;
    static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);


        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (object){
                        for(int j=0;j<1000;j++){
                            sub++;
                        }
                    }

                }
            });
        }

        Thread.sleep(3000);
        //等待线程池中的2个任务执行完毕，否则一直等待,zk分布式锁
        countDownLatch.countDown();
        System.out.println(sub);
    }

}

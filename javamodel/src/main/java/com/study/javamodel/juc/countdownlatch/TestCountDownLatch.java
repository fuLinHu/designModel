package com.study.javamodel.juc.countdownlatch;

import com.study.javamodel.juc.ThreadPool.ThreadPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class TestCountDownLatch {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CountDownLatch countDownLatch = new CountDownLatch(4);

        new Thread(()->{
            log.info("运行第一个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        }).start();
        new Thread(()->{
            log.info("运行第2个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        }).start();
        new Thread(()->{
            log.info("运行第3个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        }).start();
       /* new Thread(()->{
            log.info("运行第4个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        }).start();*/
       /* new Thread(()->{
            log.info("运行第4个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        }).start();*/
        try {
            //Thread.sleep(1000);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


       /* executorService.submit(()->{
            log.info("运行第一个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        });
        executorService.submit(()->{
            log.info("运行第2个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        });
        executorService.submit(()->{
            log.info("运行第3个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        });
        executorService.submit(()->{
            log.info("运行第4个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        });
        executorService.submit(()->{
            log.info("运行第5个----");
            countDownLatch.countDown();
            log.info("剩余---"+countDownLatch.getCount());
        });
        executorService.submit(()->{
            log.info("结束运行");
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("剩余---"+countDownLatch.getCount());
        });*/
    }

}

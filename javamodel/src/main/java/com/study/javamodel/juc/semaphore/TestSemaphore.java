package com.study.javamodel.juc.semaphore;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Semaphore;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/7 8:35
 * @Version V1.0
 */
@Slf4j
public class TestSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i <10 ; i++) {
            int j =i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    log.info("开始运行第"+j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(2000);
                    log.info("结束运行第"+j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },j+"").start();
        }
    }

}

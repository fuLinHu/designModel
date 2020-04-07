package com.study.javamodel.juc.cyclicbarrier;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class TestSyclicbarrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(3,()->{
            log.info("最终的结果");
        });
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(()->{
            try {
                log.info("第一个运行");
                Thread.sleep(1000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                Thread.sleep(1000);
                log.info("第二个运行");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                Thread.sleep(1000);
                log.info("第三个运行");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();

    }
}

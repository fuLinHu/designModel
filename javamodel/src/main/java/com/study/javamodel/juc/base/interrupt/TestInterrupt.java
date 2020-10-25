package com.study.javamodel.juc.base.interrupt;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/20 9:01
 * @Version V1.0
 */
@Slf4j
public class TestInterrupt {
    //
    public static void main(String[] args)  {
        Thread thread = new Thread(() -> {
            long s = System.currentTimeMillis();
            log.info("初始的时间为："+s);
            long k = 0;
            while (k<=4000){
                if(Thread.currentThread().isInterrupted()){
                    throw new RuntimeException("代码是可以中断");
                }
                long l = System.currentTimeMillis();
                k = l-s;
                log.info("结束的时间为："+k);
            }
        });
        thread.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}

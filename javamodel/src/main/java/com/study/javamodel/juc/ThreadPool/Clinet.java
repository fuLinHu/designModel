package com.study.javamodel.juc.ThreadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
@Slf4j
public class Clinet {
    public static void main(String[] args) {



        ThreadPool threadPool = new ThreadPool(2,1000,TimeUnit.MILLISECONDS,10);
        for (int i = 0; i < 15; i++) {
            int j=i;
            /*threadPool.exectuce(()->{
                log.info("运行第【"+j+"】");
            });*/
            threadPool.exectuce(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("运行第【"+j+"】");
                }
            });
        }
    }

}

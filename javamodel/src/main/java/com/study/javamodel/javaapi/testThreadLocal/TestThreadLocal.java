package com.study.javamodel.javaapi.testThreadLocal;

import java.util.concurrent.CountDownLatch;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/31 17:38
 * @Version V1.0
 */
public class TestThreadLocal {
    /*如上文所述，ThreadLocal 适用于如下两种场景

    每个线程需要有自己单独的实例
    实例需要在多个方法中共享，但不希望被多线程共享*/
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        ThreadLocal<StringBuffer> threadLocal = new ThreadLocal<>();
        for (int i = 0; i <10 ; i++) {
            int finalI = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(new StringBuffer().append(finalI +""));
                    StringBuffer stringBuffer = threadLocal.get();
                    System.out.println(stringBuffer.toString());
                    threadLocal.remove();
                    countDownLatch.countDown();
                }

            }).start();
        }
        countDownLatch.await();
    }
}

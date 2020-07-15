package com.study.javamodel.jvm.codegood;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/5 15:20
 * @Version V1.0
 */
public class DeadLockTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock1) {
                try {
                    System.out.println("thread1 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
                synchronized (lock2) {
                    System.out.println("thread1 end");
                }
             }
        }).start();
        new Thread(()->{
            synchronized (lock2) {
                try {
                    System.out.println("thread2 begin");
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
                synchronized (lock1) {
                    System.out.println("thread2 end");
                }
            }

        }).start();
        System.out.println("main thread end");
    }
}

package com.study.javamodel.juc.base.Reentrantlock.condiction;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/17 11:17
 * @Version V1.0
 */
public class TestCondiction {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                reentrantLock.lock();
                try {
                    condition.await();
                    System.out.println("线程-----------------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            },i+"").start();
        }
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);

    }
}

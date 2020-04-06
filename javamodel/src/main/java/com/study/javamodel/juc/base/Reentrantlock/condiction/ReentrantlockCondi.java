package com.study.javamodel.juc.base.Reentrantlock.condiction;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public  class ReentrantlockCondi {
    static Object yan = null;
    static Object fan = null;
    public static void main(String[] args) {
        ReentrantLock ROOM = new ReentrantLock();
        Condition yanRoom = ROOM.newCondition();
        Condition fanRoom = ROOM.newCondition();

        new Thread(()->{
            ROOM.lock();
            while(yan==null){
                log.info("未获取烟------");
                try {
                    yanRoom.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                log.info("获取烟---我做其他的事情");
            }finally {
                ROOM.unlock();
            }
        },"等待烟的线程").start();
        new Thread(()->{
            ROOM.lock();
            while(fan==null){
                log.info("未获饭------");
                try {
                    fanRoom.await();
                    System.out.println();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                log.info("获取饭---我做其他的事情");
            }finally {
                ROOM.unlock();
            }
        },"等待饭的线程").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            try{
                ROOM.lock();
                log.info("开始送饭");
                fan = new Object();
                fanRoom.signal();
            }finally {
                ROOM.unlock();
            }

        },"送饭的线程").start();
        new Thread(()->{
            try{
                ROOM.lock();
                log.info("开始送烟");
                yan = new Object();
                yanRoom.signal();
            }finally {
                ROOM.unlock();
            }
        },"送饭的线程").start();
    }

}


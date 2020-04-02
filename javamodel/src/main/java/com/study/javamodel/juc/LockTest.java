package com.study.javamodel.juc;

import jdk.internal.dynalink.support.TypeConverterFactory;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import jdk.nashorn.internal.ir.CatchNode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/1 8:57
 * @Version V1.0
 */
public class LockTest {
    public static void main(String[] args) {
        Tick1 tick = new Tick1();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                try {
                    tick.remove();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(tick.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tick.get());
            }).start();
        }
    }
}
class Tick1{
    private int count = 50;
    ReentrantLock reentrantLock = new ReentrantLock();
    public  void  remove() throws Exception {
        try{
            reentrantLock.lock();
            if(count>0){
                TimeUnit.MILLISECONDS.sleep(10);
                this.count=--count;
            }
        }finally {
            reentrantLock.unlock();
        }

    }
    public int get(){
        return count;
    }
}

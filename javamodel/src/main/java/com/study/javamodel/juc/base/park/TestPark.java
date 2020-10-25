package com.study.javamodel.juc.base.park;

import java.util.concurrent.locks.LockSupport;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/20 10:46
 * @Version V1.0
 */
public class TestPark {
    public static void main(String[] args) {
        TestParkObje testParkObje = new TestParkObje();
        testParkObje.test();
    }

    public static void run(){
        LockSupport.unpark(Thread.currentThread());
        System.out.println("即将进行park---");
        LockSupport.park();
        System.out.println("park结束---");
    }


}

class TestParkObje{
    public void test(){
        System.out.println("即将进行park---");
        LockSupport.park(this);
        Object blocker = LockSupport.getBlocker(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        System.out.println("park结束---");
    }
}
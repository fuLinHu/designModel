package com.study.javamodel.juc.base.interrupt;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/20 9:43
 * @Version V1.0
 */
public class TestInterrupted {
    public static void main(String[] args) {
        System.out.println(Thread.interrupted());
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());
    }
}

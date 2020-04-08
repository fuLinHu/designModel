package com.study.javamodel.juc.concurrentclasses;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/8 8:38
 * @Version V1.0
 */
public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.computeIfAbsent("1",(a)->{
            return 1;
        });
    }
}

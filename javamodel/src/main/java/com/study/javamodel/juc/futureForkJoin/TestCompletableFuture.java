package com.study.javamodel.juc.futureForkJoin;

import java.util.concurrent.CompletableFuture;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/4 8:44
 * @Version V1.0
 */
public class TestCompletableFuture {
    public static void main(String[] args) {
        CompletableFuture.runAsync(()->{

        }).thenRun(()->{

        });
    }
}

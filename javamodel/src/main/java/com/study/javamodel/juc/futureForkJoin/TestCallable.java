package com.study.javamodel.juc.futureForkJoin;

import java.util.concurrent.Callable;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/3 10:19
 * @Version V1.0
 */
public class TestCallable {
    public static void main(String[] args) throws Exception {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "你好";
            }
        };
        System.out.println("---------------------------------------");
        String call = callable.call();
        System.out.println(call);

    }

}

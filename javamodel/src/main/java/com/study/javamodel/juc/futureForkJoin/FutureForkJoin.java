package com.study.javamodel.juc.futureForkJoin;

import java.util.concurrent.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/3 8:34
 * @Version V1.0
 */
public class FutureForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        concurrentSkipListMap.put("w",3);
        concurrentSkipListMap.put("h",3);
        concurrentSkipListMap.put("a",3);
        concurrentSkipListMap.put("h",3);
        concurrentSkipListMap.put("b",3);



        Runtime runtime = Runtime.getRuntime();
        int i = runtime.availableProcessors();
        long l = runtime.freeMemory()/1024/1204;
        long l1 = runtime.maxMemory()/1024/1204;

        System.out.println("cpu 核数---------"+i);
        System.out.println("freeMemory---------"+l);
        System.out.println("maxMemory---------"+l1);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> submit = executor.submit(() -> {
            Thread.sleep(1000);
            return "nihao";
        });

        //submit.cancel(true);
        System.out.println("----------------运行到这了-----get之前--------");
        String s = submit.get();
        System.out.println("----------------运行到这了-----get之后--------"+s );
        s = submit.get();
        System.out.println("----------------运行到这了-----get之后--------"+s );
        boolean done = submit.isDone();
        System.out.println("----------------运行到这了-----done--------"+done );
        executor.shutdown();

    }
}

package com.study.javamodel.juc.webtest.interrupt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLOutput;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/15 11:06
 * @Version V1.0
 */
@Service
@Slf4j
public class InterruptService {
    private ConcurrentMap<String,Thread> concurrentMap = new ConcurrentHashMap();
    private Random random = new Random();
    private int count =0;

    public String method1(String uuStr){
        count++;
        try {
            int random=(int)(Math.random()*1000+1);
            String s = UUID.randomUUID().toString();
            Thread thread = Thread.currentThread();
            thread.setName(s);
            //concurrentMap.put(s, thread);
            //System.out.println("睡了:"+random);

           // log.info("睡了:"+random);

            //System.out.println("pre   thread-name:"+thread.getName());
            //System.out.println("睡了:"+random);
            if(count==5){
                log.info("线程5:"+Thread.currentThread().hashCode());
               // log.info("睡了5:"+random);
                log.info("pre   thread-name5:"+thread.getName());
                Date date = new Date();
                while((new Date().getTime()-date.getTime())<5000){

                }
                log.info("after thread-name5:"+thread.getName());
            }else{
                log.info("线程:"+Thread.currentThread().hashCode());
                log.info("pre   thread-name:"+thread.getName());
                Date date = new Date();
                while((new Date().getTime()-date.getTime())<100){

                }
                log.info("after thread-name:"+thread.getName());
            }
            //System.out.println("after thread-name:"+thread.getName());
           // log.info("after method1:thread-name:"+Thread.currentThread().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "method1";
    }

    public String method2(String uuStr){
        try {
            Thread thread = concurrentMap.get(uuStr);
            log.info("method2:thread-name:"+thread.getName());
            thread.interrupt();
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "method2";
    }

    public static void main(String[] args) {

        Math.random();
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }

}

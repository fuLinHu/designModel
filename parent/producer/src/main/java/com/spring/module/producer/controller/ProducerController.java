package com.spring.module.producer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import feign.template.Template;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/9 9:46
 * @Version V1.0
 */
@RestController
@RequestMapping("pro")
public class ProducerController {

    @Value("${fulinhu}")
    private String fulinhu;


    @RequestMapping("/test")
    public String test(@RequestHeader("token") String token, HttpServletRequest httpServletRequest){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int serverPort = httpServletRequest.getServerPort();
        return "this is a producer token："+token+" 【"+fulinhu+" 】serverPort【"+serverPort;
    }
    @RequestMapping("/findById/{id}")
    @SentinelResource("findByIdhot{id}")
    public String testSentinel(@PathVariable("id") String id){
        return "this is a sentinel::"+id;
    }

    @RequestMapping("/findById")
    @SentinelResource("findByIdhot")
    public String testSentinelHot( String id, Integer age){
        System.out.println(new Date());
        return "this is a sentinel::"+id+" age：："+age;
    }





    @RequestMapping("/save")
    public String save(){
        return UUID.randomUUID().toString();
    }

    @RequestMapping("/testWarm")
    public String testWarm(){
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
//        for (int i = 0; i < 1000; i++) {
//            Thread.sleep(200);
//            restTemplate.getForEntity("http://localhost:1002/pro/save",String.class);
//        }
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 100; i1++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    restTemplate.getForEntity("http://localhost:1002/pro/testWarm",String.class);
                }
            }).start();

        }
    }

}

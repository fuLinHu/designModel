package com.spring.module.producer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    @RequestMapping("/testpath")
    public String product(HttpServletRequest request){
        return "product的请求-Path的测试;请求头：【"+request.getHeader("X-Request-Foo")+"】";
    }
}

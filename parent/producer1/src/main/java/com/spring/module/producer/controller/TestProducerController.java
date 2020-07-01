package com.spring.module.producer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/27 1:08
 * @Version V1.0
 */
@RestController
@RequestMapping("/product")
public class TestProducerController {
    @RequestMapping("/test1")
    public String test1(){
        return "验证gateWay权限";
    }
}

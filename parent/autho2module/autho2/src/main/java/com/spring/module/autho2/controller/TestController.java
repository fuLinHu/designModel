package com.spring.module.autho2.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/22 13:59
 * @Version V1.0
 */
@RestController
public class TestController {
    @RequestMapping("/test")
    public String test() {
        return "autho2-------";
    }
}

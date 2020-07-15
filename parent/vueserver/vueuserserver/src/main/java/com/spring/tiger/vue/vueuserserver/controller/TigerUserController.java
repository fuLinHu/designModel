package com.spring.tiger.vue.vueuserserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/11 22:37
 * @Version V1.0
 */
@RestController
@RequestMapping("/tigerUser")
public class TigerUserController {
    @RequestMapping("/test")
    public String test(){
        return "1234567890";
    }
}

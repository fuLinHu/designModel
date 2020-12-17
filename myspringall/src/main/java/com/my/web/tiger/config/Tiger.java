package com.my.web.tiger.config;

import com.my.web.tiger.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/12 21:55
 * @Version V1.0
 */
@Component
public class Tiger {
    @Resource
    private UserService userService;
    @PostConstruct
    public void run(){
        Object information = userService.information();
        System.out.println(information);
    }
}

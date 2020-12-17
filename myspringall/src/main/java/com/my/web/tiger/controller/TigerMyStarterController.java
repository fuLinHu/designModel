package com.my.web.tiger.controller;

import com.my.web.tiger.entity.User;
import com.my.web.tiger.service.UserService;
import com.my.web.tiger.service.impl.UserServiceImpl;
import com.mystarter.myjsonspringbootstarter.service.MyJsonService;
import jdk.nashorn.internal.codegen.types.Type;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/23 11:19
 * @Version V1.0
 */
@RestController
@Slf4j
public class TigerMyStarterController {
    @Autowired
    private MyJsonService myJsonService;
 /*   @Resource
    private UserService userServiceImpl;
    @Autowired
    private UserService userServiceImpl2;*/

    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private UserService userServiceImpl2;

    @RequestMapping(value = "tojson")
    public String getStr() {
        //org.apache.tomcat.util.net.NioEndpoint
        User user = new User();
        user.setName("dsfsf");
        user.setAge(18);
        log.debug("我是debug级别的日志");
        log.info("我是info级别的日志");
        log.warn("我是warn级别的日志");
        log.error("我是error级别的日志");
        log.trace("我是trace级别的日志");
        userServiceImpl.information();
        userServiceImpl2.information();
        return myJsonService.objToJson(user);
    }

}

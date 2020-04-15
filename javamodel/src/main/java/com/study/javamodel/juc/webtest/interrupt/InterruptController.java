package com.study.javamodel.juc.webtest.interrupt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/15 10:53
 * @Version V1.0
 */
@RestController
public class InterruptController {


    @Autowired
    private InterruptService interruptService;

    @RequestMapping("/m1")
    public String method1(){
        interruptService.method1("9a96f26c-9350-4900-8c5b-d9a7f9e65280");
        return "method1";
    }

    @RequestMapping("/m2")
    public String method2(){
        interruptService.method2("9a96f26c-9350-4900-8c5b-d9a7f9e65280");
        return "method2";
    }

}

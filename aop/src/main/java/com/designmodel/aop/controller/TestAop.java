package com.designmodel.aop.controller;

import com.designmodel.aop.entity.Test;
import com.designmodel.aop.service.TestService;
import com.designmodel.aop.service.TestValiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAop {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public String test(){
        /*TestValiService testValiService=(TestValiService)testService;*/
        Test test = new Test();
        /*testValiService.validate(test);*/
        test.setAge(10);
        test.setName("fulinhu");
        testService.testService(test);
        return "aop";
    }
}

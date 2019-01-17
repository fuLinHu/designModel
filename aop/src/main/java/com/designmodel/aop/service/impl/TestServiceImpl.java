package com.designmodel.aop.service.impl;

import com.designmodel.aop.entity.Test;
import com.designmodel.aop.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    @Override
    public Test testService(Test test) {
        //System.out.println(1/0);
        System.out.println("进入方法了--------");
        return test;
    }
}

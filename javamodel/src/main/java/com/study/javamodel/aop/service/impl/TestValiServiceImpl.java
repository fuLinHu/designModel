package com.study.javamodel.aop.service.impl;


import com.study.javamodel.aop.entity.Test;
import com.study.javamodel.aop.service.TestValiService;
import org.springframework.stereotype.Service;

@Service("testValiService")
public class TestValiServiceImpl implements TestValiService {
    @Override
    public boolean validate(Test test) {
        System.out.println("验证数据是否正确----------"+test);
        return false;
    }
}

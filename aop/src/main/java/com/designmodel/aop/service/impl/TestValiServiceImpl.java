package com.designmodel.aop.service.impl;

import com.designmodel.aop.entity.Test;
import com.designmodel.aop.service.TestValiService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("testValiService")
public class TestValiServiceImpl implements TestValiService {
    @Override
    public boolean validate(Test test) {
        System.out.println("验证数据是否正确----------"+test);
        return false;
    }
}

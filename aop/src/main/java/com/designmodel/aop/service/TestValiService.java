package com.designmodel.aop.service;

import com.designmodel.aop.entity.Test;
import org.springframework.stereotype.Service;

public interface TestValiService {
    public boolean validate(Test test);
}

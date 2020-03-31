package com.study.javamodel.aop.service;

import com.study.javamodel.aop.entity.Test;
import org.springframework.stereotype.Service;

public interface TestValiService {
    public boolean validate(Test test);
}

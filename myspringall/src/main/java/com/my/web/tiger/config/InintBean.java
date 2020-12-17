package com.my.web.tiger.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/11 17:43
 * @Version V1.0
 */
//@Component
public class InintBean implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(beanName);
        return null;
    }
}

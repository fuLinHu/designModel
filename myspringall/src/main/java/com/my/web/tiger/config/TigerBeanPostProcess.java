package com.my.web.tiger.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/14 14:07
 * @Version V1.0
 */
@Component
public class TigerBeanPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization : "+beanName);
        if("server-org.springframework.boot.autoconfigure.web.ServerProperties".equals(beanName)){
            return bean;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }
}

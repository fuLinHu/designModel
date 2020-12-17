package com.my.web.tiger.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
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
public class PopulateBean implements InstantiationAwareBeanPostProcessor {
   /* @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if("tiger".equals(beanName)){

        }
        return new Tiger();
    }*/

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        /*if("tiger".equals(beanName)){
            return false;
        }*/
        System.out.println("属性填充::"+beanName);
        return true;
    }

   /* @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println(JSONObject.toJSON(pvs));
        System.out.println("==================="+beanName);
        return null;
    }*/
}

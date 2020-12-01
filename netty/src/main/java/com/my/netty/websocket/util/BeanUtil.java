package com.my.netty.websocket.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/27 19:09
 * @Version V1.0
 */
@Component
public class BeanUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static Object getBeanByName(String name){
        return applicationContext.getBean(name);
    }

    public static Object getBeanByType(Class t){
         return applicationContext.getBean(t);
    }

}

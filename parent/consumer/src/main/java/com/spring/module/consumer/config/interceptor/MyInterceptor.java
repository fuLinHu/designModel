package com.spring.module.consumer.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/9 18:18
 * @Version V1.0
 */
public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token", UUID.randomUUID().toString());
    }
}

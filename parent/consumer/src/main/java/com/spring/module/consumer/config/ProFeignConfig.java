package com.spring.module.consumer.config;

import com.spring.module.consumer.config.interceptor.MyInterceptor;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/9 17:15
 * @Version V1.0
 */
public class ProFeignConfig {
    @Bean
    public Logger.Level level() {
       //return Logger.Level.FULL;
       return Logger.Level.BASIC;
     }

     @Bean
     public RequestInterceptor requestInterceptor(){
        return new MyInterceptor();
     }
}

package com.luban.orderserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/6/8 19:31
 * @Version V1.0
 */
@Component
public class NacosConfigTest {
    @Value("${fulinhu}")
    private String name;
    //@Value("${fulinhu1}")
    private String name1;


    @PostConstruct
    public void init(){

        System.out.println("是否加载了================="+name);

        System.out.println("是否加载了product-server.yaml================="+name1);
    }
}

package com.luban.productserver.config;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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
@RefreshScope  //@Value注解可以获取到配置中心的值，但是无法动态感知修改后的值，需要利用@RefreshScope注解
public class NacosConfigTest {
    @Value("${fulinhu}")
    private String name;
    @Value("${fulinhu1}")
    private String name1;

    public String getName() {
        return name;
    }

    @PostConstruct
    public void init(){

        System.out.println("是否加载了================="+name);

        System.out.println("是否加载了product-server.yaml================="+name1);
    }






}

package com.mystarter.myjsonspringbootstarter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/23 10:48
 * @Version V1.0
 */

@ConfigurationProperties(prefix = "ziyou.json")
public class MyJsonProperties {
    public static final String DEFAULT_NAME = "ziyou";

    private String name = DEFAULT_NAME;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

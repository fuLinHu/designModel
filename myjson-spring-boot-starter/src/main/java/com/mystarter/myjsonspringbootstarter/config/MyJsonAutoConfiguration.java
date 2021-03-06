package com.mystarter.myjsonspringbootstarter.config;

import com.mystarter.myjsonspringbootstarter.service.MyJsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/23 10:50
 * @Version V1.0
 */
@Configuration
@ConditionalOnClass({MyJsonService.class})
@EnableConfigurationProperties(MyJsonProperties.class)
public class MyJsonAutoConfiguration {
    /**
     * 注入属性类
     */
    @Autowired
    private MyJsonProperties myJsonProperties;

    /**
     * 当当前上下文中没有 MyJsonService 类时创建类
     *
     * @return 返回创建的实例
     */
    @Bean
    @ConditionalOnMissingBean(MyJsonService.class)
    public MyJsonService myJsonService() {
        MyJsonService myJsonService = new MyJsonService();
        myJsonService.setName(myJsonProperties.getName());
        return myJsonService;
    }

}

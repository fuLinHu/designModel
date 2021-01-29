package com.elk.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoELKApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoELKApplication.class, args);
        Logger logger = LoggerFactory.getLogger(DemoELKApplication.class);
        logger.info("10：26测试log付林虎new  [createUser:{}]","付林虎");
        logger.debug("这是个debug的级别");
        logger.warn("这是个warn的级别");
        logger.error("这是个error的级别");
        int i = "付林虎".hashCode();
        System.out.println(i);
        int i1 = "QQ".hashCode();
        System.out.println(i1);
    }




}

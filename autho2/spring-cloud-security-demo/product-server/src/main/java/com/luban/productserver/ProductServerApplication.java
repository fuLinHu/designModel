package com.luban.productserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ProductServerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ProductServerApplication.class, args);
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProductServerApplication.class, args);
        String userName = applicationContext.getEnvironment().getProperty("fulinhu");
        String userAge = applicationContext.getEnvironment().getProperty("common.age");
        System.out.println("common name :"+userName+"; age: "+userAge);
    }

}

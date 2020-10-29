package com.my.netty.websocket.annotation;

import org.springframework.stereotype.Component;


import java.lang.annotation.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 9:23
 * @Version V1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface NettyMapping {
    String name() default "";
    String value() default "";
}

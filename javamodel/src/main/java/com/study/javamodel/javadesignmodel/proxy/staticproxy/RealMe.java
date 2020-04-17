package com.study.javamodel.javadesignmodel.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 15:28
 * @Version V1.0
 */
@Slf4j
public class RealMe implements Car {
    @Override
    public void run() {
       log.info("我是车主，但是我喝醉了，不能开车");
    }
}

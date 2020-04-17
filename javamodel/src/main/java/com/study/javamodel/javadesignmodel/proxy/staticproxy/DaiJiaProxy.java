package com.study.javamodel.javadesignmodel.proxy.staticproxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 15:30
 * @Version V1.0
 */
@Slf4j
public class DaiJiaProxy implements Car {

    RealMe realMe;
    public DaiJiaProxy(){
        realMe= new RealMe();
    }
    public void call(){
        log.info("打电话联系客户");
    }

    @Override
    public void run() {
        call();
        realMe.run();
        pay();
    }
    public void pay(){
        log.info("到家了给我钱");
    }
}

package com.study.javamodel.javadesignmodel.adaptermodel;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/17 13:22
 * @Version V1.0
 */
//网线
@Slf4j
public class Adaptee {
    public void request(){
        log.info("连线上网！");
    }
}

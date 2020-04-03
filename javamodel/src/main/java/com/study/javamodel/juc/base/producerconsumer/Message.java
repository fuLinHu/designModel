package com.study.javamodel.juc.base.producerconsumer;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 10:49
 * @Version V1.0
 */
@Getter
@Slf4j
public final class Message {
    private Integer id;
    private Object value;
    public Message(Integer id,Object value){
        this.id=id;
        this.value=value;
    }
}

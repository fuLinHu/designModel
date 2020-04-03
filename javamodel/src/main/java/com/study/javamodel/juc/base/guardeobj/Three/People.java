package com.study.javamodel.juc.base.guardeobj.Three;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 9:16
 * @Version V1.0
 */
@Slf4j
public class People extends Thread{
    @Override
    public void run(){
        GuardeObject guardeObject = EmailBox.createGuardeObject();
        //等待 2000ms去获取 信号 如果2000ms获取到则进行剩余操作  否则  退出等待
        log.info("people 开始等待邮件---");
        Object o = guardeObject.get(2000);
        log.info("people 获取到的邮件---id :{},值是{}",guardeObject.getId(),o );
    }
}

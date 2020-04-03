package com.study.javamodel.juc.base.guardeobj.Three;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 9:17
 * @Version V1.0
 */
@Slf4j
public class PostMan extends Thread{
    private Integer key;
    private String email;

    public PostMan(Integer key,String email){
        this.email = email;
        this.key = key;
    }
    @Override
    public void run(){
        log.info("开始发送 邮件----id::{},内容：：{}",key,email);
        GuardeObject guardeObject = EmailBox.getGuardeObject(key);
        guardeObject.pass(email);
        log.info("邮件已经发出----id::{},内容：：{}",key,email);
    }
}

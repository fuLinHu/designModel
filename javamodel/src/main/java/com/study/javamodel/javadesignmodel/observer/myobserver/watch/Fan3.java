package com.study.javamodel.javadesignmodel.observer.myobserver.watch;

import com.study.javamodel.javadesignmodel.observer.myobserver.event.SenWeiBoEvent;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 9:34
 * @Version V1.0
 */
public class Fan3 implements Observer<SenWeiBoEvent> {
    @Override
    public void doAction(SenWeiBoEvent senWeiBoEvent) {
        System.out.println("明星发来的消息：："+senWeiBoEvent.getMessage());
        System.out.println("【"+this.getClass().getName()+"】做出的反应：："+senWeiBoEvent.getMessage());
    }
}

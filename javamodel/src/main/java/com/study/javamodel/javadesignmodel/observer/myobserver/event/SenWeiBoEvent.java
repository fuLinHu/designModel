package com.study.javamodel.javadesignmodel.observer.myobserver.event;

import com.study.javamodel.javadesignmodel.observer.myobserver.watched.Star;

/**
 * @className
 * @Description TODO 事件对象 包含一个事件源 即被观察者本身(明星信息)
 * @Author 付林虎
 * @Date 2020/8/5 9:24
 * @Version V1.0
 */
public class SenWeiBoEvent extends Event<Star> {
    private Star resouce;
    private Object message;

    public Object getMessage() {
        return message;
    }

    public SenWeiBoEvent(Star resouce, Object message) {
        this.resouce = resouce;
        this.message = message;
    }

    @Override
    Star getResouce() {
        return this.resouce;
    }
}

package com.study.javamodel.javadesignmodel.listener;

import java.util.EventListener;

/**
 * @className
 * @Description TODO 事件监听器
 * @Author 付林虎
 * @Date 2020/4/23 16:28
 * @Version V1.0
 */
public interface DoorListener extends EventListener {
    //EventListener是所有事件侦听器接口必须扩展的标记接口、因为它是无内容的标记接口、
    //所以事件处理方法由我们自己声明如下：
    public void dealDoorEvent(DoorEvent event);
}

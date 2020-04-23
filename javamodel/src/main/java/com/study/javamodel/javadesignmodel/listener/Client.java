package com.study.javamodel.javadesignmodel.listener;

/**
 * 1、给事件源注册监听器。
 * 2、组件接受外部作用，也就是事件被触发。
 * 3、组件产生一个相应的事件对象，并把此对象传递给与之关联的事件处理器。
 * 4、事件处理器启动，并执行相关的代码来处理该事件。
 **/

import java.util.Observable;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/23 16:43
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        DoorResource resource = new DoorResource();
        resource.addDoorListener(new FrontDoorListener());
        resource.fireOpend();
        //-----------------------

        DoorEvent doorEvent = new DoorEvent(resource);
        doorEvent.setDoorState("0");
        resource.notifyDoors(doorEvent);
    }
}

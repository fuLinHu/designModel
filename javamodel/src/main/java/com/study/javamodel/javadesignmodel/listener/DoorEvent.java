package com.study.javamodel.javadesignmodel.listener;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @className
 * @Description TODO 事件对象
 * @Author 付林虎
 * @Date 2020/4/23 16:25
 * @Version V1.0
 */

public class DoorEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public DoorEvent(Object source) {
        super(source);
    }
    private String doorState; //表示门的状态

    public void setDoorState(String doorState){
        this.doorState = doorState;
    }

    public String getDoorState(){
        return this.doorState;
    }
}

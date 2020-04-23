package com.study.javamodel.javadesignmodel.listener;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO 事件源
 * @Author 付林虎
 * @Date 2020/4/23 16:37
 * @Version V1.0
 */
public class DoorResource {
    private List<DoorListener> listeners = new ArrayList<>();

    //添加监听器
    public void addDoorListener(DoorListener listener){
        synchronized (this){
            if (listener != null && !(listeners.contains(listener))){
                listeners.add(listener);
            }
        }
    }
    //删除监听器
    public void removeDoorListener(DoorListener listener){
        synchronized (this){
            if (listeners.contains(listener)){
                listeners.remove(listener);
            }
        }
    }
    //调用监听器
    public void notifyDoors(DoorEvent event) {
        for (DoorListener iDoorListener : listeners){
            iDoorListener.dealDoorEvent(event);
        }
    }

    /**
     * 模拟开门事件
     */
    public void fireOpend(){
        if (listeners == null){
            return;
        }
        DoorEvent event = new DoorEvent(this);
        event.setDoorState("1");
        notifyDoors(event);
    }
}

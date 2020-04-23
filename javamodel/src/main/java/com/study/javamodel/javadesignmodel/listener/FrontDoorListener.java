package com.study.javamodel.javadesignmodel.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * @className
 * @Description TODO 事件监听器 监听门的一个状态
 * @Author 付林虎
 * @Date 2020/4/23 16:32
 * @Version V1.0
 */
@Slf4j
public class FrontDoorListener implements DoorListener {
    @Override
    public void dealDoorEvent(DoorEvent event) {
        String doorState = event.getDoorState();
        if("0".equals(doorState)){
            log.info("门的状态是关闭的");
        }else if("1".equals(doorState)){
            log.info("门的状态是打开的");
        }
    }
}

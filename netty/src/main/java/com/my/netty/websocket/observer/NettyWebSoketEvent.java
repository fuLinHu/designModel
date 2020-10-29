package com.my.netty.websocket.observer;

import java.util.EventObject;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 14:24
 * @Version V1.0
 */
public class NettyWebSoketEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public NettyWebSoketEvent(Object source) {
        super(source);
    }


}

package com.my.netty.websocket.handlermapping;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 9:58
 * @Version V1.0
 */
@Data
public class RequestObject {
    //参数集合  有序的
    private List<Object> param;
    private ChannelHandlerContext channelHandlerContext;
    private TextWebSocketFrame textWebSocketFrame;
    private Object bean;
    private Method method;
    private LinkedHashMap<String,MethodParamMeta> requestParam = new LinkedHashMap<>();
}

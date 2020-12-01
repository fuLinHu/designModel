package com.my.netty.websocket.observer;

import com.my.netty.websocket.config.NettyWebSocketHandler;
import com.my.netty.websocket.handlermapping.Mapping;
import com.my.netty.websocket.handlermapping.NettyHandlerMapping;
import com.my.netty.websocket.handlermapping.RequestObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 14:18
 * @Version V1.0
 */
@Component
@Async
@Slf4j
public class PublisEventWebSocket  {

    @Resource
    private NettyHandlerMapping nettyHandlerMapping;
    @Resource
    private NettyWebSocketHandler nettyWebSocketHandler;

    public void publisEvent(NettyWebSoketEvent nettyWebSoketEvent) throws Exception {
        try {
            log.info(Thread.currentThread().getName());
            List list = (List)nettyWebSoketEvent;
            Mapping mapping = nettyHandlerMapping.getMapping();
            Map<String, RequestObject> methodMap = mapping.getMethodMap();
            Set<Map.Entry<String, RequestObject>> entries = methodMap.entrySet();
            for (Map.Entry<String, RequestObject> entry : entries) {
                String key = entry.getKey();
                if(list.contains(key)){
                    RequestObject value = entry.getValue();
                    ChannelHandlerContext channelHandlerContext = value.getChannelHandlerContext();
                    TextWebSocketFrame textWebSocketFrame = value.getTextWebSocketFrame();
                    nettyWebSocketHandler.channelRead(channelHandlerContext,textWebSocketFrame);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publisEvent() throws Exception {
        try {
            log.info(Thread.currentThread().getName());
            Mapping mapping = nettyHandlerMapping.getMapping();
            Map<String, RequestObject> methodMap = mapping.getMethodMap();
            Set<Map.Entry<String, RequestObject>> entries = methodMap.entrySet();
            for (Map.Entry<String, RequestObject> entry : entries) {
                RequestObject value = entry.getValue();
                ChannelHandlerContext channelHandlerContext = value.getChannelHandlerContext();
                TextWebSocketFrame textWebSocketFrame = value.getTextWebSocketFrame();
                nettyWebSocketHandler.channelRead(channelHandlerContext, textWebSocketFrame);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

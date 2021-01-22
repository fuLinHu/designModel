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
           /* log.info(Thread.currentThread().getName());
            List list = (List)nettyWebSoketEvent;
            Mapping mapping = nettyHandlerMapping.getMapping();
            Map<String, Set<RequestObject>> methodMap = mapping.getMethodMap();
            Set<Map.Entry<String, Set<RequestObject>>> entries = methodMap.entrySet();
            for (Map.Entry<String, Set<RequestObject>> entry : entries) {
                String key = entry.getKey();
                if(list.contains(key)){
                    Set<RequestObject> value = entry.getValue();
                    for (RequestObject requestObject : value) {
                        ChannelHandlerContext channelHandlerContext = requestObject.getChannelHandlerContext();
                        TextWebSocketFrame textWebSocketFrame = requestObject.getTextWebSocketFrame();
                        nettyWebSocketHandler.channelRead(channelHandlerContext,textWebSocketFrame);
                    }
                }
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publisEvent(){
        try {
            log.info(Thread.currentThread().getName());
            Mapping mapping = nettyHandlerMapping.getMapping();
            Map<String, Map<ChannelHandlerContext, RequestObject>> methodMap = mapping.getMethodMap();

            Set<Map.Entry<String, Map<ChannelHandlerContext, RequestObject>>> entries = methodMap.entrySet();
            for (Map.Entry<String,Map<ChannelHandlerContext, RequestObject>> entry : entries) {
                Map<ChannelHandlerContext, RequestObject> value = entry.getValue();
                for (Map.Entry<ChannelHandlerContext, RequestObject> channelHandlerContextRequestObjectEntry : value.entrySet()) {
                    RequestObject requestObject = channelHandlerContextRequestObjectEntry.getValue();
                    ChannelHandlerContext channelHandlerContext = requestObject.getChannelHandlerContext();
                    if(channelHandlerContext==null){
                        continue;
                    }
                    TextWebSocketFrame textWebSocketFrame = requestObject.getTextWebSocketFrame();
                    nettyWebSocketHandler.channelRead(channelHandlerContext, textWebSocketFrame);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}

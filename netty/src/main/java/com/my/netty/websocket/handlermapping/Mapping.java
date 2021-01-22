package com.my.netty.websocket.handlermapping;

import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 9:43
 * @Version V1.0
 */
@Component
public class Mapping {
    @Getter
    private Map<String, Map<ChannelHandlerContext,RequestObject>> methodMap = new ConcurrentHashMap<>();
    @Getter
    private Map<String,RequestObject> sourceRequestObjectMap = new HashMap<>();

    public void addSourceMapping(String url,RequestObject requestObject){
        this.sourceRequestObjectMap.put(url,requestObject);
    }
    public RequestObject getSourceMapping(String url){
        RequestObject requestObject = this.sourceRequestObjectMap.get(url);
        return copy(requestObject);
    }

    private RequestObject copy(RequestObject requestObject){
        RequestObject requestObject1 = new RequestObject();
        requestObject1.setRequestParam(requestObject.getRequestParam());
        requestObject1.setChannelHandlerContext(requestObject.getChannelHandlerContext());
        requestObject1.setMethod(requestObject.getMethod());
        requestObject1.setBean(requestObject.getBean());
        return requestObject1;
    }





    public void addMapping(String url,Map<ChannelHandlerContext,RequestObject> requestObject){
        this.methodMap.put(url,requestObject);
    }
    public Map<ChannelHandlerContext,RequestObject> getMapping(String url){
        if(this.methodMap.get(url)==null){
            synchronized (this){
                if(this.methodMap.get(url)==null){
                    this.methodMap.put(url,new ConcurrentHashMap<>());
                }
            }
        }
        //Map<ChannelHandlerContext,RequestObject>
        return this.methodMap.get(url);
    }



    public boolean containsPath(String url){
        return this.sourceRequestObjectMap.containsKey(url);
    }


}

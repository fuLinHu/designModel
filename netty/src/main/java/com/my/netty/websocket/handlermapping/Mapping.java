package com.my.netty.websocket.handlermapping;

import lombok.Getter;
import org.springframework.stereotype.Component;

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
    private Map<String, RequestObject> methodMap = new ConcurrentHashMap<String, RequestObject>();
    public void addMapping(String url,RequestObject requestObject){
        this.methodMap.put(url,requestObject);
    }
    public RequestObject getMapping(String url){
        return this.methodMap.get(url);
    }

    public boolean containsPath(String url){
        return this.methodMap.containsKey(url);
    }


}

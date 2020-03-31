package com.study.javamodel.javaapi.TestMap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/31 15:54
 * @Version V1.0
 */
public class TestCurrentMap {
    public static void main(String[] args) {
        //HashMap？ConcurrentHashMap？相信看完这篇没人能难住你！ https://blog.csdn.net/weixin_44460333/article/details/86770169

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("1","fulinhu");

    }
}

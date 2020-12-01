package com.my.netty.websocket.controller;


import com.my.netty.websocket.annotation.NettyMapping;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 9:29
 * @Version V1.0
 */
@NettyMapping("/websoket")
public class NewCountController {
    @NettyMapping("/HELLO")
    public String doit(String uid,String  gid){
        return "/HELLO------uid::"+uid+" gid::"+gid;
    }
}

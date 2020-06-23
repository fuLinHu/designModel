package com.my.netty.me;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/3 9:32
 * @Version V1.0
 */
public class MeServerChannelHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        if(s.contains("&&")){
            String[] split = s.split("&&");
            for (String s1 : split) {
                System.out.println("服务器端接收到的数据::"+s1);
            }
        }

    }
}

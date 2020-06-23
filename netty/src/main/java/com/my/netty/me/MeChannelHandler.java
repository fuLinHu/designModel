package com.my.netty.me;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/3 9:30
 * @Version V1.0
 */
public class MeChannelHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端发送的数据"+s);
    }
}

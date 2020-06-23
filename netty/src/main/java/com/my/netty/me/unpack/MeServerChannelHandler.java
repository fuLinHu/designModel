package com.my.netty.me.unpack;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/3 9:32
 * @Version V1.0
 */
public class MeServerChannelHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object s) throws Exception {
        System.out.println("服务器端接收到的数据::"+s);

    }
}

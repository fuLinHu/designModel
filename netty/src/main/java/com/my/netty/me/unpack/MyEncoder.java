package com.my.netty.me.unpack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/4 9:08
 * @Version V1.0
 */
public class MyEncoder extends MessageToByteEncoder<MyPackage> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyPackage myPackage, ByteBuf byteBuf) throws Exception {
//        byteBuf.writeBytes();
    }
}

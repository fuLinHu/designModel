package com.my.netty.me.unpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/3 8:50
 * @Version V1.0
 */
public class NettyClinet {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {

            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline()
                        .addLast(new ObjectEncoder())
                        .addLast(new ObjectDecoder(30,
                                ClassResolvers.cacheDisabled(null)))
                        .addLast(new MeChannelHandler());

            }
        });
        Channel channel = bootstrap.connect("127.0.0.1", 7000).sync().channel();
        int i =0;
        while(i<100){
            i++;
            MyPackage myPackage = new MyPackage();
            myPackage.setMessage("付林虎1989&&"+i);
            channel.writeAndFlush(myPackage);
        }
    }
}

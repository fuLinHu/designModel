package com.study.javamodel.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOSocketCline {
    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open(new InetSocketAddress("127.0.0.1", 99));
        open.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("付林虎发送的数据22".getBytes());
        byteBuffer.flip();
        open.write(byteBuffer);
        open.close();
    }

}

package com.study.javamodel.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BioSocketServer1 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.bind(new InetSocketAddress(99));
        SocketChannel accept = open.accept();
        FileChannel open1 = FileChannel.open(Paths.get("F:\\高级架构65.mp4"),  StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);
        while (accept.read(byteBuffer)>-1){
            byteBuffer.flip();
            open1.write(byteBuffer);
            byteBuffer.clear();
        }
        byteBuffer.put("你好，我已经接受完毕".getBytes());
        byteBuffer.flip();
        accept.write(byteBuffer);

        open.close();
        accept.close();
        open1.close();
    }
}

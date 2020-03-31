package tes;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BioSocketClinet1 {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 99));
        FileChannel open = FileChannel.open(Paths.get("F:\\高级架构\\第五节(水平分割取摸算法案例).mp4"), StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (open.read(byteBuffer)>-1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        socketChannel.shutdownOutput();
        ByteBuffer byteBuffer1 = ByteBuffer.allocate(1024);
        while (socketChannel.read(byteBuffer1)>-1){
            byteBuffer1.flip();
            byteBuffer1.get();
            System.out.println(new String(byteBuffer1.array()));
        }

        socketChannel.close();
        open.close();
    }
}

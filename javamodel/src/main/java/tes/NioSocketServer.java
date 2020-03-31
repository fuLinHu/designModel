package tes;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.configureBlocking(false);
        ServerSocketChannel bind = open.bind(new InetSocketAddress(99));

        Selector selector = Selector.open();
        SelectionKey register = bind.register(selector, SelectionKey.OP_ACCEPT);
        Iterator<SelectionKey> iterator =null;
            while (selector.select()>0){
                iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey next = iterator.next();
                    if(next.isAcceptable()){
                        //客户端 accept被触发了
                        ServerSocketChannel serverChannel = (ServerSocketChannel)next.channel();

                        SocketChannel clientChannel = serverChannel.accept();
                        System.out.println("channel is acceptable");
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector,SelectionKey.OP_READ);
                    }else if(next.isReadable()){
                        SocketChannel channel = (SocketChannel) next.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        while (channel.read(byteBuffer)>-1){
                            byteBuffer.flip();
                            System.out.println(new String(byteBuffer.array()));
                            byteBuffer.clear();
                        }
                    }
                }
            }
            //iterator.remove();
        }
}

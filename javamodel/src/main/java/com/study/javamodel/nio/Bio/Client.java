package com.study.javamodel.nio.Bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/28 8:32
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("localhost",9000);
            socket.getOutputStream().write("付林虎".getBytes());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}

package com.study.javamodel.nio.Bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/28 8:33
 * @Version V1.0
 */
@Slf4j
public class Service {
    public static void main(String[] args) {
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(9000);
            log.info("链接socket-----");
            while(true){
                log.info("等待接收数据-----");
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                byte[] bytes = new byte[1024];
                log.info("等待读取数据-----");
                inputStream.read(bytes);
                log.info("读取数据-----");
                String str = new String(bytes);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

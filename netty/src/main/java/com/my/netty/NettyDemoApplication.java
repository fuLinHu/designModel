package com.my.netty;


import com.my.netty.websocket.config.NettyServer;
import com.my.netty.websocket.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class NettyDemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(NettyDemoApplication.class, args);
        NettyServer nettyServer = (NettyServer) BeanUtil.getBeanByType(NettyServer.class);
        try {
            nettyServer.start();
        } catch (Exception e) {
            log.error("netty wesocket is error .....");
            e.printStackTrace();
        }
    }

}

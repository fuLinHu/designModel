package com.forest.tiger.rabbit.publish;

import com.forest.tiger.rabbit.utils.RabbitConstant;
import com.forest.tiger.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;
import java.util.Scanner;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/11/30 8:59
 * @Version V1.0
 */
public class Producter {
    public static void main(String[] args) throws Exception {
        //获取TCP长连接
        Connection conn = RabbitUtils.getConnection();
        //创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = conn.createChannel();
        //创建交换机
        channel.exchangeDeclare(RabbitConstant.EXCHANGE_WEATHER,"fanout");
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.err.println("---------handle  return----------");
                System.err.println("replyCode: " + replyCode);
                System.err.println("replyText: " + replyText);
                System.err.println("exchange: " + exchange);
                System.err.println("routingKey: " + routingKey);
                System.err.println("properties: " + properties);
                System.err.println("body: " + new String(body));
            }
        });
        while (true){
            String input = new Scanner(System.in).next();

            //四个参数
            //exchange 交换机，暂时用不到，在后面进行发布订阅时才会用到
            //队列名称
            //额外的设置属性
            //最后一个参数是要传递的消息字节数组
            channel.basicPublish(RabbitConstant.EXCHANGE_WEATHER, "", null,input.getBytes());
        /*channel.close();
        conn.close();
        System.out.println("===发送成功===");*/
        }

    }
}

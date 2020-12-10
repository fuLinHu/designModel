package com.forest.tiger.rabbit.springbootrabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.AMQImpl;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/3 8:38
 * @Version V1.0
 */
@Component
public class Customer {
    @RabbitListener(queues = "queuehello")
    @RabbitHandler
    public void listener(Message message, Channel channel){
        // 采用手动应答模式, 手动确认应答更为安全稳定
        try {
           channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("queuehello: " + new String(message.getBody()));
    }

    @RabbitListener(queues = "boot_queue")
    public void listenerHello(Message message,Channel channel){
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(new String(message.getBody()));
    }
}

package com.forest.tiger.rabbit.springbootrabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/3 8:37
 * @Version V1.0
 */
@Component
public class Product {

    @Autowired
    private AmqpTemplate template;
    @Autowired
    private RabbitTemplate rabbitTemplate;



    public void run()  {
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
            if(ack){
                System.out.println("发送消息成功"+"  "+cause);
            }else{
                System.out.println("发送消息失败"+"  "+cause);
            }
        });
        rabbitTemplate.setReturnsCallback((o)->{
            Message message = o.getMessage();
            System.out.println("返回的数据"+new String(message.getBody()));
        });
        for (int i = 0; i < 20; i++) {
            String s = "boot.this is :" + i;
            if(i==10||i==2){
                s = "this is :" + i;
            }else{
                s = "boot.this is :" + i;
            }
            rabbitTemplate.convertAndSend("boot_topic_ex",s, s);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        template.convertAndSend("queuehello","我是hello");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

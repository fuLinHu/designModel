package com.forest.tiger.rabbit.publish;

import com.forest.tiger.rabbit.utils.RabbitConstant;
import com.forest.tiger.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/11/30 8:59
 * @Version V1.0
 */
public class Consumer1 {
    public static void main(String[] args) throws Exception {
        //获取TCP长连接
        Connection conn = RabbitUtils.getConnection();
        //创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = conn.createChannel();
        //创建队列
        channel.queueDeclare(RabbitConstant.QUEUE_SINA,false,false,false,null);
        channel.queueBind(RabbitConstant.QUEUE_SINA,RabbitConstant.EXCHANGE_WEATHER,"");
        // 可以设置为5  这个样 当接收5个以后再进行 ack 则可以继续接收数据，当不足5个数据的时候  不进行 ack  直到满足5个数据。
        //这个时候   channel.basicAck(envelope.getDeliveryTag() , true);   最后一个参数设置为true 者一次ack 则会将 者5条数据全部提交

        //        channel.basicQos(1); 为1 的时候 尽量 hannel.basicAck(envelope.getDeliveryTag() , false);，一次ack 只提交一条数据
        channel.basicQos(5);
        final List<String>[] conter = new List[]{new ArrayList<>()};
        channel.basicConsume(RabbitConstant.QUEUE_SINA,false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String s = new String(body);
                conter[0].add(s);
                String string = envelope.toString();
                System.out.println("百度天气收到气象信息：" + s +" enveiope::"+string);
                if(conter[0].size()==5){
                    /*for (String s1 : conter[0]) {
                        
                    }*/
                    conter[0] = new ArrayList<>();
                    channel.basicAck(envelope.getDeliveryTag() , true);
                    System.out.println("=================================================");
                }
            }
        });
    }
}

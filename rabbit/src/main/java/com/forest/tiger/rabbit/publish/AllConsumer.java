package com.forest.tiger.rabbit.publish;

import com.forest.tiger.rabbit.utils.RabbitConstant;
import com.forest.tiger.rabbit.utils.RabbitUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/30 16:27
 * @Version V1.0
 */
public class AllConsumer {
    public static void main(String[] args) {
        Map<String,Channel> channelMap = new HashMap<>();


        Map<Channel,Long> map = new HashMap<>();
        //获取TCP长连接
        Connection conn = RabbitUtils.getConnection();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CountDownLatch countDownLatch1 = new CountDownLatch(10);
        List<CountDownLatch> list =new ArrayList<>();
        list.add(countDownLatch);
        new Thread(()->{
            while(true){

            }

        },"scan_zhengce");

        for (int i = 0; i < 10; i++) {
            try {
                //创建通信“通道”，相当于TCP中的虚拟连接
                Channel channel = conn.createChannel();
                //创建队列
                channel.queueDeclare(RabbitConstant.QUEUE_BAIDU+i,false,false,false,null);
                channel.queueBind(RabbitConstant.QUEUE_BAIDU+i,RabbitConstant.EXCHANGE_WEATHER,"");
                //
                channel.basicQos(1);
                int finalI = i;
                channel.basicConsume(RabbitConstant.QUEUE_BAIDU+i,false,new DefaultConsumer(channel){
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("新浪天气收到气象信息：" + new String(body)+" "+consumerTag);
                        System.out.println(finalI+"___"+Thread.currentThread().getName());
                        map.put(channel,envelope.getDeliveryTag());
                        list.get(0).countDown();
                        //channel.basicAck(envelope.getDeliveryTag() , false);
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            while (true){
                list.get(0).await();
                //获取新加如的政策
                //如果新加如的政策n 大于0  则CountDownLatch(10+n)
                //创建新得消费者 用于下一次消费
                list.set(0,new CountDownLatch(10));
                if(map.size()>0){
                    for (Map.Entry<Channel, Long> channelLongEntry : map.entrySet()) {
                        Channel k = channelLongEntry.getKey();
                        Long v = channelLongEntry.getValue();
                        if(k.isOpen()){
                            System.out.println(v);
                            try {
                                k.basicAck(v,false);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                //System.out.println("消费完成");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Channel publish(Connection conn) throws IOException {
        //创建通信“通道”，相当于TCP中的虚拟连接
        Channel channel = conn.createChannel();
        return channel;
    }


}

package org.apache.rocketmq.example.foresttiger.broadcasting;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/11 14:29
 * @Version V1.0
 */
public class ConsumerBroadCastMember1 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_broadcast");
        consumer.setNamesrvAddr("192.168.118.128:9876");
        // 批量消费,每次拉取10条
        consumer.setConsumeMessageBatchMaxSize(10);
        //设置广播消费
        consumer.setMessageModel(MessageModel.BROADCASTING);
        //设置集群消费
//        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅PushTopic下Tag为push的消息
        consumer.subscribe("topic_broadcast", "TagA || Tag B || Tage C");
        consumer.registerMessageListener(new MessageListenerConcurrently(){
                //然后在消费者端：消费者会从多个消息队列上去拿消息。这时虽然每个消息队列上的消息是有序
                //的，但是多个队列之间的消息仍然是乱序的。消费者端要保证消息有序，就需要按队列一个一个
                //来取消息，即取完一个队列的消息后，再去取下一个队列的消息。而给consumer注入的
                //MessageListenerOrderly对象，在RocketMQ内部就会通过锁队列的方式保证消息是一个一个队
                //列来取的。MessageListenerConcurrently这个消息监听器则不会锁队列，每次都是从多个
                //Message中取一批数据（默认不超过32条）。因此也无法保证消息有序


            //MessageListenerConcurrently
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                if(list!=null&&list.size()>0){
                    for (MessageExt messageExt : list) {
                        byte[] body = messageExt.getBody();
                        String str = new String(body);
                        System.out.println("接收得消息为："+str);
                    }
                }
                return null;
            }
        });
        consumer.start();
        System.out.println("Consumer1 Started.");

    }
}

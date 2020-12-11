package org.apache.rocketmq.example.foresttiger.nobroadcasting;

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
public class ConsumerBroadCastMember4 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer_broadcast");
        consumer.setNamesrvAddr("192.168.118.128:9876");
        // 批量消费,每次拉取10条
        consumer.setConsumeMessageBatchMaxSize(10);
        //设置广播消费
       // consumer.setMessageModel(MessageModel.BROADCASTING);
        //设置集群消费
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 订阅PushTopic下Tag为push的消息
        consumer.subscribe("topic_broadcast", "TagA || Tag B || Tage C");
        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                if(list!=null&&list.size()>0){
                    for (MessageExt messageExt : list) {
                        byte[] body = messageExt.getBody();
                        String str = new String(body);
                        System.out.println("接收得消息为："+str);
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer2 Started.");

    }
}

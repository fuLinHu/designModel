package com.study.javamodel.juc.base.producerconsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 10:47
 * @Version V1.0
 */
//java 线程间进行通信
@Slf4j
public class MessageQueue {
    //消息的队列组合
    private LinkedList<Message> messageList = new LinkedList<>();
    //容量
    private int capcity;

    public MessageQueue (int capcity){
        this.capcity=capcity;
    }
    public Message take(){
        //检查队列是否为空 针对messageList对象 加锁
        synchronized (messageList){
            log.info("检查队列是否为空");
            while(messageList.isEmpty()){
                try {
                    //针对 messageList对象等待 wait()必须放到同步代码块中。。。。。。
                    log.info("队列为空则进行等待");
                    messageList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
            log.info("消费者队列不为空则进行消费");
            log.info("消费者唤醒所有等待线程");
            return messageList.removeFirst();
        }
    }
    public void put(Message message){
        synchronized (messageList){
            //检查队列是否已经满了
            log.info("检查队列是否已经满了");
            while (messageList.size()>=capcity){
                try {
                    //如果满员则 等待
                    log.info("如果满员则 等待");
                    messageList.wait();
                    System.out.println("正在等待---------ing");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果有空余则进行 添加
            log.info("如果有空余则进行 添加");
            messageList.addLast(message);
            //唤醒消息
            log.info("生产者唤醒所有等待线程");
            messageList.notifyAll();
        }

    }

}

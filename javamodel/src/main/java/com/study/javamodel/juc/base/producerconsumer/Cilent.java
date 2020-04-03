package com.study.javamodel.juc.base.producerconsumer;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 11:22
 * @Version V1.0
 */
public class Cilent {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        for (int i = 0; i < 3; i++) {
            int id =i;
            new Thread(()->{
                messageQueue.put(new Message(id,id+"值"));
            },"生产者"+i).start();
        }

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                messageQueue.take();
            }
        },"消费者").start();


    }
}

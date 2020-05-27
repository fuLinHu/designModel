package com.my.zookeeper.zkclient.agent;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/5/12 9:04
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Agent.premain(null, null);
        runCPU(2); //20% 占用
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //
    private static void runCPU(int count) {
        for (int i = 0; i < count; i++) {
            new Thread(() -> {
                while (true) {
                    long bac = 1000000;
                    bac = bac >> 1;
                }
            }).start();
            ;
        }
    }
}

package com.study.javamodel.juc;

import java.util.concurrent.TimeUnit;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/1 8:30
 * @Version V1.0
 */
public class SyncTest {
    public static void main(String[] args) {
        Tick tick = new Tick();
        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                try {
                    tick.remove();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(tick.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(tick.get());
            }).start();
        }

    }
}
class Tick{
    private int count = 50;
    public synchronized void  remove() throws Exception {
        if(count>0){
            TimeUnit.MILLISECONDS.sleep(10);
            this.count=--count;
        }
    }
    public int get(){
        /*try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return count;
    }
}


package com.study.javamodel.javadesignmodel.singlmodel;

import java.util.concurrent.TimeUnit;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 9:47
 * @Version V1.0
 */
//线程不安全
public class LanHan1 {
    private LanHan1(){

    }

    private static LanHan1 lanHan1=null;
    public static LanHan1 getInstance(){
        if(lanHan1==null){
            lanHan1 = new LanHan1();
        }
        return lanHan1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LanHan1 instance = LanHan1.getInstance();
                    LanHan1 instance1 = LanHan1.getInstance();
                    if(instance!=instance1){
                        System.out.println("线程不安全---");
                    }
                }
            });
            t.start();
        }


    }
}

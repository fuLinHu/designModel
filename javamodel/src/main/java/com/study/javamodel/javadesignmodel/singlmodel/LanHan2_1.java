package com.study.javamodel.javadesignmodel.singlmodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 9:47
 * @Version V1.0
 */
//线程安全
public class LanHan2_1 {
    private LanHan2_1(){

    }

    private static LanHan2_1 lanHan1=null;
    public synchronized static LanHan2_1 getInstance(){
        if(lanHan1==null){
            lanHan1 = new LanHan2_1();
        }
        return lanHan1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LanHan2_1 instance = LanHan2_1.getInstance();
                    LanHan2_1 instance1 = LanHan2_1.getInstance();
                    if(instance!=instance1){
                        System.out.println("线程不安全---");
                    }
                }
            });
            t.start();
        }


    }
}

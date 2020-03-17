package com.study.javamodel.javadesignmodel.singlmodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 9:47
 * @Version V1.0
 */
//线程安全
public class LanHan2_0 {
    private LanHan2_0(){

    }

    private static LanHan2_0 lanHan1=null;
    public static LanHan2_0 getInstance(){
        synchronized (LanHan2_0.class){
            if(lanHan1==null){
                lanHan1 = new LanHan2_0();
            }
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

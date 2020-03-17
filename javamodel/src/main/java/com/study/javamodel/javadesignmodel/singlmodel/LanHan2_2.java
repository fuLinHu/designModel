package com.study.javamodel.javadesignmodel.singlmodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 9:47
 * @Version V1.0
 */
//线程安全
public class LanHan2_2 {



    private static volatile LanHan2_2 lanHan1;

    private LanHan2_2(){

    }

    public static LanHan2_2 getInstance(){
        if(lanHan1==null){
            synchronized (LanHan2_2.class){
                if(lanHan1==null){
                    lanHan1 = new LanHan2_2();
                }

            }
        }
        return lanHan1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LanHan2_2 instance = LanHan2_2.getInstance();
                    LanHan2_2 instance1 = LanHan2_2.getInstance();
                    if(instance!=instance1){
                        System.out.println("线程不安全---");
                    }
                }
            });
            t.start();
        }


    }
}

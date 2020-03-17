package com.study.javamodel.javadesignmodel.singlmodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/17 13:21
 * @Version V1.0
 */
public class StaticInner {
    private StaticInner(){

    }

    private static class InnerClass{
        private final static StaticInner staticInner = new StaticInner();
    }

    public static StaticInner getInstance(){
       return InnerClass.staticInner;
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
                    StaticInner instance = StaticInner.getInstance();
                    StaticInner instance1 = StaticInner.getInstance();
                    if(instance!=instance1){
                        System.out.println("线程不安全---");
                    }
                }
            });
            t.start();
        }


    }
}

package com.study.javamodel.javadesignmodel.singlmodel;

public class ColorEnumMain {
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
                    ColorEnum instance = ColorEnum.RED;
                    ColorEnum instance1 = ColorEnum.RED;
                    instance.doSomething();
                    if(instance!=instance1){
                        System.out.println("线程不安全---");
                    }
                }
            });
            t.start();
        }


    }
}
enum ColorEnum {
    RED;
    public void doSomething() {
        System.out.println("doSomething");
    }

}

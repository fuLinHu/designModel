package com.study.javamodel.jvm.jvmdetail;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/17 11:18
 * @Version V1.0
 */
public class ShutdownHook {
    public static void main(java.lang.String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.out.println("-------关闭钩子--------------");
        }));
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

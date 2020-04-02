package com.study.javamodel.juc.base.guardeobj.one;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/2 18:24
 * @Version V1.0
 */

public class GuardeObject {
    public static void main(String[] args) {
        GuardeObjectModel guardeObjectModel = new GuardeObjectModel();
        new Thread(()->{
            System.out.println("启动线程等待获取下载结果："+Thread.currentThread().getName());
            Object o = guardeObjectModel.get();
            System.out.println("获取到下载结果，然后运行其他逻辑："+Thread.currentThread().getName());
        },"t1").start();

        new Thread(()->{
            System.out.println("开始下载任务，"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardeObjectModel.complate(new Object());
            System.out.println("完成下载任务，我可以执行其他操作："+Thread.currentThread().getName());

        },"t2").start();
    }

}

class GuardeObjectModel{
    private Object respon;
    public Object get(){
        synchronized (this){
            System.out.println("判断是否有数据："+Thread.currentThread().getName());
            while (respon==null){
                try {
                    System.out.println("等待获取respon："+Thread.currentThread().getName());
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("获取结果执行其他逻辑！："+Thread.currentThread().getName());
        }
        return respon;
    }
    public void complate(Object respon){
        synchronized (this){
            this.respon=respon;
            this.notifyAll();
        }
    }
}

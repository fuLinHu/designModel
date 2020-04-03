package com.study.javamodel.juc.base.guardeobj.two;

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
            Object o = guardeObjectModel.get(900);
            System.out.println("获取到下载结果，然后运行其他逻辑："+Thread.currentThread().getName());
        },"t1").start();

        new Thread(()->{
            System.out.println("开始下载任务，"+Thread.currentThread().getName());
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            guardeObjectModel.complate(null);
            System.out.println("完成下载任务，我可以执行其他操作："+Thread.currentThread().getName());

        },"t2").start();
    }

}

class GuardeObjectModel{
    private Object respon;
    public Object get(long waiteTime){
        synchronized (this){
            System.out.println("判断是否有数据："+Thread.currentThread().getName());
            long l = System.currentTimeMillis();
            long passTime = 0;
            while (respon==null){
                long otherTime = waiteTime-passTime;
                if(otherTime<0){
                    System.out.println("超时退出运行：："+Thread.currentThread().getName());
                    throw new RuntimeException("timeout -----------");
                }
                try {
                    System.out.println("等待获取respon："+Thread.currentThread().getName());
                    this.wait(otherTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passTime= System.currentTimeMillis()-l;
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

package com.study.javamodel.juc.base.guardeobj.Three;

import lombok.Data;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 8:55
 * @Version V1.0
 */
@Data
public class GuardeObject {
    private int id; //传递的对象 对应的主键
    private Object respon; //传递的对象

    public GuardeObject(int id){
        this.id=id;
    }

    //获取 respon信号对象
    public Object get(long waitTime){
        synchronized (this){
            long begin = System.currentTimeMillis();
            long now =0;
            while(respon==null){
                if(now<0){
                    throw new RuntimeException("等待超时-------");
                }
                try {
                    this.wait(waitTime-now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            now = System.currentTimeMillis() - begin;

        }
        return respon;
    }


    //传递信号
    public void pass(Object respon){
        synchronized (this){
            this.respon=respon;
            //唤醒所有wait 线程
            this.notifyAll();
        }
    }

}

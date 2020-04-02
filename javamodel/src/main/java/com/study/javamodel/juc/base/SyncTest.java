package com.study.javamodel.juc.base;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/2 10:00
 * @Version V1.0
 */
public class SyncTest {
    //锁的 必须是同一个对象
    //成员同步方法  等同 同步代码块（this） 如果
    //静态同步方法  等同 同步代码块（class）
    public static void main(String[] args) {
        Class<SyncTest> syncTestClass = SyncTest.class;
        System.out.println(syncTestClass == syncTestClass);//true
    }

    private synchronized void des(Integer count){
        if(count<0){

       }

    }
}
class lock{

}
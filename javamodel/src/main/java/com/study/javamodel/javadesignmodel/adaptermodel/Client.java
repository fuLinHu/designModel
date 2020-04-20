package com.study.javamodel.javadesignmodel.adaptermodel;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/17 13:24
 * @Version V1.0
 */
//客户端类 电脑
public class Client {


    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        NetToSub netToSub = new Adapter(adaptee);
        netToSub.handeler();
    }
}

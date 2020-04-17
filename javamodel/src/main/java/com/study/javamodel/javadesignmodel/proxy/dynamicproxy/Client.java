package com.study.javamodel.javadesignmodel.proxy.dynamicproxy;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 16:28
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        //真实的角色
        UserService userService = new UserServiceImpl();
        //创建调用代理类
        InveHanderProxy inveHanderProxy = new InveHanderProxy();
        //传递啊真实对象给代理创造器
        inveHanderProxy.setInterfaceProxy(userService);
        //获取代理对象
        UserService proxy = (UserService)inveHanderProxy.getProxy();
        //运行被代理类的方法
        proxy.add(new User("fulinhu",30,new Date(),188));
        proxy.delete("1111");
        proxy.updte(new User("fulinhu",31,new Date(),188));
        proxy.select();
    }
}

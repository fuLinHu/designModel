package com.study.javamodel.javadesignmodel.observer;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/23 17:33
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        //一个被观察者 对应多个观察者  被观察者改变  则会通知其他观察者
        Watched watched = new Watched();
        new Watcher(watched);
        new Watcher(watched);
        watched.changeData("1");
        watched.changeData("2");
        watched.changeData("3");
        watched.changeData("4");
    }
}
/**
 *当对象间存在一对多关系时，则使用观察者模式（Observer Pattern）。
 * 观察者模式定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 **/

/**
 优点：
 1、解耦，被观察者只知道观察者列表「抽象接口」，被观察者不知道具体的观察者
 2、被观察者发送通知，所有注册的观察者都会收到信息「可以实现广播机制」

 缺点：
 1、如果观察者非常多的话，那么所有的观察者收到被观察者发送的通知会耗时
 2、观察者知道被观察者发送通知了，但是观察者不知道所观察的对象具体是如何发生变化的
 3、如果被观察者有循环依赖的话，那么被观察者发送通知会使观察者循环调用，会导致系统崩溃
 **/
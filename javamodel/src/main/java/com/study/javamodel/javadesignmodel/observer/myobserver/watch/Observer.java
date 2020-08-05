package com.study.javamodel.javadesignmodel.observer.myobserver.watch;

/**
 * @className
 * @Description TODO 观察者
 * @Author 付林虎
 * @Date 2020/8/5 8:54
 * @Version V1.0
 */
public interface Observer<T> {

    //观察者 监控  被观察者 被观察者发生改变  观察者坐出反应
    void doAction(T t);

}

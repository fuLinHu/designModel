package com.study.javamodel.javadesignmodel.observer.myobserver.watched;

import com.study.javamodel.javadesignmodel.observer.myobserver.watch.Observer;

import java.util.Vector;

/**
 * @className
 * @Description TODO 被观察者  被观察者  会发出信息  去通知观察者 这里就是明星
 * @Author 付林虎
 * @Date 2020/8/5 9:19
 * @Version V1.0
 */
public abstract class Observered<T>  {
    private Vector<Observer> obs ;

    public Observered() {
        obs = new Vector<>();
    }


    //增加
    public void attach(Observer observer){
        if(observer!=null&&!obs.contains(observer)){
            obs.add(observer);
        }
    }
   //删除
    public  void detach(Observer observer){
        if(observer!=null){
            obs.remove(observer);
        }
    }
     //通知
    public  void notifyObservers(T t){
        for (Observer ob : obs) {
            ob.doAction(t);
        }
    }
    //状态
    public abstract void setAction(Object action);

    public abstract Object getAction();
}

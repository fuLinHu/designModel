package com.study.javamodel.javadesignmodel.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @className
 * @Description TODO 监视者
 * @Author 付林虎
 * @Date 2020/4/23 17:28
 * @Version V1.0
 */
public class Watcher implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Data has been changed to:'"+((Watched)o).retrieveData()+"'");
    }
    public Watcher(Watched w){
        w.addObserver(this);
    }
}

package com.study.javamodel.javadesignmodel.observer.myobserver.watched;

import com.study.javamodel.javadesignmodel.observer.myobserver.event.SenWeiBoEvent;


/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 9:54
 * @Version V1.0
 */
public class Star extends Observered<SenWeiBoEvent> {

    private String action;
    @Override
    public void setAction(Object action) {
        this.action = (String) action;
        SenWeiBoEvent senWeiBoEvent = new SenWeiBoEvent(this,action);
        notifyObservers(senWeiBoEvent);
    }

    @Override
    public Object getAction() {
        return this.action;
    }
}

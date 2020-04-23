package com.study.javamodel.javadesignmodel.observer;

import java.util.Observable;

/**
 * @className
 * @Description TODO 被监视的对象
 * @Author 付林虎
 * @Date 2020/4/23 17:27
 * @Version V1.0
 */
public class Watched extends Observable{
    private String data="";
    /*
     * 取值方法
     * */
    public String retrieveData(){
        return data;
    }
    /*
     * 改值方法
     * */
    public void changeData(String data){
        if(!this.data.equals(data)){
            this.data=data;
            setChanged();
        }
        notifyObservers();
    }


}

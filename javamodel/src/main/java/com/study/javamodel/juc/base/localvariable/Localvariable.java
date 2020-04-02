package com.study.javamodel.juc.base.localvariable;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO  局部变量的安全性
 * @Author 付林虎
 * @Date 2020/4/2 11:11
 * @Version V1.0
 */
public class Localvariable {

    public static void main(String[] args) {
        /*unsafe unsafe = new unsafe();
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                unsafe.run();
            }).start();
        }*/
        Safe safe = new Safe();
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                safe.run();
            }).start();
        }
    }
}

class unsafe{
    List<Integer> list = new ArrayList<>();
    public void run(){
        for (int i = 0; i < 100; i++) {
            add();
            remove();
        }

    }
    private void remove(){
        list.remove(0);
    }

    private void add(){
        list.add(1);
    }

}

class Safe{
    public void run(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            add(list);
            remove(list);
        }

    }

    private void remove(List<Integer> list){
        //这样不安全
        new Thread(()->{
            list.remove(0);
        }).start();
        //这样是安全
        list.remove(0);


    }

    private void add(List<Integer> list){
        list.add(1);
    }

}




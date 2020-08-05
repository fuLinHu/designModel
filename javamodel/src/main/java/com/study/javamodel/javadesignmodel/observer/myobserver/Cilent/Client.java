package com.study.javamodel.javadesignmodel.observer.myobserver.Cilent;

import com.study.javamodel.javadesignmodel.observer.myobserver.watch.Fan1;
import com.study.javamodel.javadesignmodel.observer.myobserver.watch.Fan2;
import com.study.javamodel.javadesignmodel.observer.myobserver.watch.Fan3;
import com.study.javamodel.javadesignmodel.observer.myobserver.watched.Star;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 10:37
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Star star = new Star();
        star.attach(new Fan1());
        star.attach(new Fan2());
        star.attach(new Fan3());
        star.setAction("我今天分享了一篇报道----，记着点赞啊亲");
    }
}

package com.study.javamodel.juc.copyandwrite;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/11 8:59
 * @Version V1.0
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("小红");
    }
}

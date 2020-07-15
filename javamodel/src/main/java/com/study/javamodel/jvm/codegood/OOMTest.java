package com.study.javamodel.jvm.codegood;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/7/5 15:06
 * @Version V1.0
 */
public class OOMTest {
    public static List<Object> list =new ArrayList<>();
    // JVM设置6// ‐Xms10M ‐Xmx10M ‐XX:+PrintGCDetails ‐XX:+HeapDumpOnOutOfMemoryError ‐XX:HeapDumpPath=D:\jvm.dump7
    public static void main(String[] args){
        int i=0;
        while(true){
            list.add(new User(i++, UUID.randomUUID().toString()));
        }
    }


}

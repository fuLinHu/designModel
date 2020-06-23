package com.study.javamodel.juc.objectpool.lock;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 19:58
 * @Version V1.0
 */
public class SessionObject {
    private byte[] bytes;
    public  SessionObject(){
        bytes = new byte[10*1024];
    }
    public String find(){
        return "获取数据--------";
    }
}

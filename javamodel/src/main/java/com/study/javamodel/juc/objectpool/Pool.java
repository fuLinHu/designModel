package com.study.javamodel.juc.objectpool;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/18 9:01
 * @Version V1.0
 */
public interface Pool<T> {
    public T get();
    public void put(T t);
    public Integer getCount();
}

package com.study.javamodel.javadesignmodel.proxy.dynamicproxy;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/16 16:00
 * @Version V1.0
 */
public interface UserService {
    public void add(Object obj);
    public void delete(String id);
    public void updte(Object obj);
    public Object select();

}

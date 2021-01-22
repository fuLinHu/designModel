package com.my.netty.websocket.util;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/11/10 22:34
 * @Version V1.0
 */
public class ReflectUtil {

    public static void getAllClass(Class cla, List<Class> returnParam){
        returnParam.add(cla);
        Class cl = null;
        if((cl = cla.getSuperclass())!=null&&!"java.lang.Object".equals(cl.getName())){
            getAllClass(cl,returnParam);
        }
    }
}

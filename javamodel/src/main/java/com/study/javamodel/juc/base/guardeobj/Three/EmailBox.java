package com.study.javamodel.juc.base.guardeobj.Three;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/3 9:17
 * @Version V1.0
 */
public class EmailBox {

    private static Integer count=0;

    //信号对象 map集合
    public static Map<Integer,GuardeObject> guardeObjectMap = new Hashtable<>();

    public static synchronized Integer getParmaayKey(){
       return  ++count;
    }

    //根据主键获取对应的 信号信息
    public static GuardeObject getGuardeObject(Integer key){
        return guardeObjectMap.remove(key);
    }

    //创建一个信号信息
    public static GuardeObject createGuardeObject(){
        GuardeObject guardeObject = new GuardeObject(getParmaayKey());
        guardeObjectMap.put(guardeObject.getId(),guardeObject);
        return guardeObject;
    }

    //获取所有信号信息的主键
    public static Set<Integer> getGuardeIds(){
        return guardeObjectMap.keySet();
    }

}

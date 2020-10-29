package com.my.netty.websocket.handlermapping;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 9:18
 * @Version V1.0
 */
@Component("nettyHandlerMapping")
public class NettyHandlerMapping  {
    @Resource
    @Getter
    private Mapping mapping;

    public Object handler(String path, List<Object> param){
        RequestObject mapping = this.mapping.getMapping(path);
        Object bean = mapping.getBean();
        Method method = mapping.getMethod();
        Object invoke =null;
        try {
            if((param==null||param.size()<=0)){
                invoke = method.invoke(bean);
            }else{
                Object[] objects = param.toArray();
                invoke = method.invoke(bean, objects);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }

    public RequestObject handlerParam(String path,Map param) throws IllegalAccessException, InstantiationException {
        RequestObject requestObject = this.mapping.getMapping(path);
        LinkedHashMap<String, MethodParamMeta> requestParam = requestObject.getRequestParam();
        requestObject.setParam(new ArrayList<>());
        if(requestParam!=null&&requestParam.size()>0){
            Set<Map.Entry<String, MethodParamMeta>> entries = requestParam.entrySet();
            for (Map.Entry<String, MethodParamMeta> entry : entries) {
                String key = entry.getKey();
                MethodParamMeta value = entry.getValue();
                Class<?> type = value.getType();
                Object o = param.get(key);
                if("java.lang.Object".equals(type.getName())){
                    Object instance = type.newInstance();
                    Class<?> aClass = instance.getClass();
                    ReflectionUtils.doWithFields(aClass, field->{
                        field.setAccessible(true);
                        String name = field.getName();
                        if(name.equals(key)){
                            field.set(key,o);
                        }
                    });
                    requestObject.getParam().add(instance);
                }else{
                    requestObject.getParam().add(o);
                }
            }
        }
        return requestObject;

    }
}

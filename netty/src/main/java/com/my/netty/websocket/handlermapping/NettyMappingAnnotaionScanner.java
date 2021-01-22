package com.my.netty.websocket.handlermapping;

import com.my.netty.websocket.annotation.NettyMapping;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Parameter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/28 9:34
 * @Version V1.0
 */
@Component
public class NettyMappingAnnotaionScanner implements BeanPostProcessor {
    @Resource
    private Mapping mapping;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> aClass = bean.getClass();
        String path = null;

        if(aClass.isAnnotationPresent(NettyMapping.class)){
            NettyMapping annotation = aClass.getAnnotation(NettyMapping.class);
            path = getValue(annotation);
        }
        String finalPath = path;
        ReflectionUtils.doWithMethods(aClass, method->{
            if(method.isAnnotationPresent(NettyMapping.class)){
                NettyMapping annotation = method.getAnnotation(NettyMapping.class);
                Parameter[] parameters = method.getParameters();
                RequestObject requestObject = new RequestObject();
                if(parameters!=null&&parameters.length>0){
                    for (Parameter parameter : parameters) {
                        String name = parameter.getName();
                        Class<?> type = parameter.getType();
                        MethodParamMeta methodParamMeta = new MethodParamMeta();
                        methodParamMeta.setName(name);
                        methodParamMeta.setType(type);
                        requestObject.getRequestParam().put(name,methodParamMeta);
                    }
                }

                String value = getValue(annotation);

                requestObject.setBean(bean);
                requestObject.setMethod(method);
                String s = finalPath + value;
                if(mapping.containsPath(s)){
                    throw new RuntimeException("存在多个相同的路径："+s);
                }
                mapping.addSourceMapping(s,requestObject);
            }
        });
        return bean;
    }

    private String getValue(NettyMapping annotation){
        String value = annotation.value();
        String name = annotation.name();
        return (value==null||"".equals(value))?name:value;
    }
}

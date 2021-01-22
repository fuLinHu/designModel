package com.my.netty.websocket.handlermapping;


import com.my.netty.websocket.util.JavaUtil;
import com.my.netty.websocket.util.ReflectUtil;
import com.my.netty.websocket.util.StringToDateConverter;
import io.netty.channel.ChannelHandlerContext;
import lombok.Getter;
import org.springframework.core.convert.converter.Converter;
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
    private static Converter converter = new StringToDateConverter();


    public Object handler(RequestObject requestObject, List<Object> param){
        Object invoke =null;
        Object bean = requestObject.getBean();
        Method method = requestObject.getMethod();
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

    public void handlerParam(String path, Map param) throws IllegalAccessException, InstantiationException {
        Map<ChannelHandlerContext, RequestObject> mapping = this.mapping.getMapping(path);
        if(mapping !=null&& mapping.size()>0){
            for (Map.Entry<ChannelHandlerContext, RequestObject> channelHandlerContextRequestObjectEntry : mapping.entrySet()) {
                RequestObject value = channelHandlerContextRequestObjectEntry.getValue();
                doRequestObject(value,param);
            }
        }
    }

    /**
     * @Author 付林虎
     * @Description //TODO  给requestObject 赋值 参数 param
     * @Date 2020/12/9 16:15
     * @Param [requestObject, param]
     * @Version V1.0
     * @return void
     **/
    public void doRequestObject(RequestObject requestObject,Map param) throws IllegalAccessException, InstantiationException {
        LinkedHashMap<String, MethodParamMeta> requestParam = requestObject.getRequestParam();
        requestObject.setParam(new ArrayList<>());
        if(requestParam!=null&&requestParam.size()>0){
            Set<Map.Entry<String, MethodParamMeta>> entries = requestParam.entrySet();
            for (Map.Entry<String, MethodParamMeta> entry : entries) {
                String key = entry.getKey();
                MethodParamMeta value = entry.getValue();
                Class<?> type = value.getType();
                String name1 = type.getName();

                if(!JavaUtil.isBaseType(type)){
                    Object instance = type.newInstance();
                    Class<?> aClass = instance.getClass();
                    List<Class> returnParam = new ArrayList<>();
                    ReflectUtil.getAllClass(aClass,returnParam);
                    for (Class aClass1 : returnParam) {
                        ReflectionUtils.doWithFields(aClass1, field->{
                            field.setAccessible(true);
                            String name = field.getName();
                            Object o = param.get(name);
                            if(o!=null&&!"".equals(o)){
                                if("java.util.Date".equals(field.getType().getName())){
                                    Date convert = (Date) converter.convert(o);
                                    field.set(instance,convert);
                                }else{
                                    field.set(instance,o);
                                }
                            }
                        });
                    }
                    requestObject.getParam().add(instance);
                }else if("java.util.Date".equals(name1)){
                    Object o = param.get(key);
                    Object convert = converter.convert(o);
                    requestObject.getParam().add(convert);
                }else if("java.lang.Integer".equals(name1)||"int".equals(name1)){
                    Object o = param.get(key);
                    requestObject.getParam().add(Integer.parseInt(o+""));
                }else if("java.lang.Double".equals(name1)||"double".equals(name1)){
                    Object o = param.get(key);
                    requestObject.getParam().add(Double.parseDouble(o+""));
                }else if("java.lang.Float".equals(name1)||"float".equals(name1)){
                    Object o = param.get(key);
                    requestObject.getParam().add(Float.parseFloat(o+""));
                }else{
                    Object o = param.get(key);
                    requestObject.getParam().add(o);
                }
            }
        }
    }



    public static void main(String[] args) {
        System.out.println(int.class.getName());
    }
}

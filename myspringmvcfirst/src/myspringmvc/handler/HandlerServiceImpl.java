package myspringmvc.handler;

import myspringmvc.annotation.MyService;
import myspringmvc.argsresolver.ArgResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@MyService("handlerServiceImpl")
public class HandlerServiceImpl implements HandlerService {
    @Override
    public Object[] hand(HttpServletRequest re, HttpServletResponse res, Method method, Map<String, Object> beans) {
        Class<?>[] parameterTypes = method.getParameterTypes ();
        Object[] args = new Object[parameterTypes.length];
        Map<String,Object> argResolvers= getInstanceType(beans,ArgResolver.class);
        int index = 0;
        int i =0;
        for (Class<?> parameterType : parameterTypes) {
            for (Map.Entry<String, Object> stringObjectEntry : argResolvers.entrySet()) {
                ArgResolver argResolver = (ArgResolver)stringObjectEntry.getValue();
                if(argResolver.support(parameterType,index,method)){
                    Object o = argResolver.argumentResolver(re, res, parameterType, index, method);
                    args[i++] = o;
                }
            }
            index++;
        }



        return new Object[0];
    }

    private Map<String,Object> getInstanceType(Map<String,Object> beans, Class<?> classType) {
        Map<String,Object> resultBeans = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = beans.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Class<?>[] interfaces = entry.getValue().getClass().getInterfaces();
            if(interfaces!=null&&interfaces.length>0){
                for (Class<?> anInterface : interfaces) {
                    if(anInterface.isAssignableFrom(classType)){
                        resultBeans.put(entry.getKey(),entry.getValue());
                    }
                }
            }
        }
        return resultBeans;
    }
}

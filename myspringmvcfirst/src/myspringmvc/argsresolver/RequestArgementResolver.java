package myspringmvc.argsresolver;

import myspringmvc.annotation.MyRequestParam;
import myspringmvc.annotation.MyService;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@MyService("requestArgementResolver")
public class RequestArgementResolver implements ArgResolver {
    @Override
    public boolean support(Class<?> type, int index, Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Annotation[] parameterAnnotation = parameterAnnotations[index];
        for(Annotation item:parameterAnnotation){
            if(item instanceof MyRequestParam){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type,int index, Method method) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        Annotation[] parameterAnnotation = parameterAnnotations[index];
        for(Annotation item:parameterAnnotation){
            if(item instanceof MyRequestParam){
                MyRequestParam item1 = (MyRequestParam) item;
                String value = item1.value();
                return request.getParameter(value);
            }
        }
        return null;
    }
}

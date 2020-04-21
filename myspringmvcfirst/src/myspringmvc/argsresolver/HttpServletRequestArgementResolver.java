package myspringmvc.argsresolver;

import myspringmvc.annotation.MyService;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@MyService("httpServletRequestArgementResolver")
public class HttpServletRequestArgementResolver implements ArgResolver {
    @Override
    public boolean support(Class<?> type, int index, Method method) {
        //判断当前type 类是否为ServletRequest的子类
        return ServletRequest.class.isAssignableFrom(type);
    }

    @Override
    public Object argumentResolver(HttpServletRequest request, HttpServletResponse response, Class<?> type,int index ,Method method) {
        return request;
    }
}

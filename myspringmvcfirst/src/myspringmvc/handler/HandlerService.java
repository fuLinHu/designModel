package myspringmvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Map;

public interface HandlerService {
    public Object[] hand(HttpServletRequest re, HttpServletResponse res, Method method,Map<String,Object> beans);
}

package myspringmvc.argsresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public interface ArgResolver {
    public boolean support(Class<?> type, int index, Method method);
    public Object argumentResolver(HttpServletRequest request, HttpServletResponse response,Class<?> type,int index,Method method);

}

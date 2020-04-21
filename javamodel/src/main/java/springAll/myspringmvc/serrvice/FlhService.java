package springAll.myspringmvc.serrvice;

import commit.entity.User;
import springAll.myspringmvc.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 16:23
 * @Version V1.0
 */
public interface FlhService {
    public String query(String age,String name);
    public void insert(User user);
}

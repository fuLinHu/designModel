package mytomcat.serlvet;

import lombok.extern.slf4j.Slf4j;
import mytomcat.request.MyRequest;
import mytomcat.response.MyResponse;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 14:24
 * @Version V1.0
 */
@Slf4j
public abstract class Servlet {
    public abstract void doGet(MyRequest myRequest, MyResponse myResponse);
    public abstract void doPost(MyRequest myRequest, MyResponse myResponse);
    public  void service(MyRequest request, MyResponse response){
        if ("GET".equals(request.getMethod())) {
            doGet(request, response);
        } else if ("POST".equals(request.getMethod())) {
            doPost(request, response);
        } else {
            log.error("暂不支持的请求方式！");
        }
    }
}

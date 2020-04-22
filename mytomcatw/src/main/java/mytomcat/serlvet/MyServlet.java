package mytomcat.serlvet;

import mytomcat.request.MyRequest;
import mytomcat.response.MyResponse;
import mytomcat.utils.HttpUtil;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 14:26
 * @Version V1.0
 */
public class MyServlet extends Servlet {
    public void doGet(MyRequest myRequest, MyResponse myResponse) {
        myResponse.wirte(HttpUtil.getHttpResponseContext200("<h1>This is request GET<h1>"));
    }

    public void doPost(MyRequest myRequest, MyResponse myResponse) {
        myResponse.wirte(HttpUtil.getHttpResponseContext200("<h1>This is request POST<h1>"));
    }
}

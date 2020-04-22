package mytomcat.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 14:31
 * @Version V1.0
 */
public class ServletConfigMapping {
    public  static List<ServletConfig> config = new ArrayList<ServletConfig>();
    static {
        config.add(new ServletConfig("MyServlet","/flh","mytomcat.serlvet.MyServlet"));
    }

}

package mytomcat.client;

import mytomcat.server.MyTomcat;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 11:18
 * @Version V1.0
 */
public class MyTomcatClient {
    public static void main(String[] args) {
        MyTomcat myTomcat = new MyTomcat(90);
        myTomcat.start();
    }
}

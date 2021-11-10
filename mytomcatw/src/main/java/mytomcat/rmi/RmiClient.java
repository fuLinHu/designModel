package mytomcat.rmi;



import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/4 19:34
 * @Version V1.0
 */
public class RmiClient {
    public static void main(String[] args) {
        InputStream inputStream =new ByteArrayInputStream("fulinhu".getBytes());
        FilterInputStream filterInputStream;
    }
}

package mytomcat.request;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 10:40
 * @Version V1.0
 */
@Slf4j
@Data
public class MyRequest {
    private String url;
    private String method;
    private InputStream inputStream;
    public MyRequest(InputStream inputStream) throws IOException {
        int i = 0;
        /*while(i==0){
            i = inputStream.available();
        }*/
        byte[] bytes= null;
        bytes = new byte[inputStream.available()];
        inputStream.read(bytes);
        extracFileds(new String(bytes,"UTF-8"));
    }
    private void extracFileds(String content){
        if(content==null||"".equals(content)){
           log.info("请求内容为空");
        }else{
            String firstLine = content.split("\\n")[0];
            String[] split = firstLine.split("\\s");
            setUrl(split[1]);
            setMethod(split[0]);
        }
    }
}

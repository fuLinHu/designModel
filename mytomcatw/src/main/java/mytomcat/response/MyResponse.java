package mytomcat.response;

import lombok.extern.slf4j.Slf4j;
import mytomcat.utils.FileUtil;
import mytomcat.utils.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 9:38
 * @Version V1.0
 */
public class MyResponse {
    public OutputStream outputStream;
    public static final String responsebody="HTTP/1.1 200+\r\n"+"Content-Type：text/html+\r\n" +"\r\n";
    public MyResponse(OutputStream outputStream){
        this.outputStream=outputStream;
    }
    public void close(){
        if(outputStream!=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void wirteHtml(String path){
        String resourcePath = FileUtil.getResourcePath(path);
        File file = new File(resourcePath);
        if(file.exists()){
            try {
                FileUtil.writeFile(file,outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            wirte(HttpUtil.getHttpResponseContext404());
        }
    }

    public void wirte(String content){
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

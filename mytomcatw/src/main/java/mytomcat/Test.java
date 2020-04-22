package mytomcat;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 9:29
 * @Version V1.0
 */
@Slf4j
public class Test {
    public static final String responsebody="HTTP/1.1 200+\r\n"+"Content-Type：text/html+\r\n" +"\r\n";
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(999);
        while (!serverSocket.isClosed()){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            String s = "";
            while((s = bufferedReader.readLine())!=null&&!"".equals(s)){
                System.out.println(s);
            }
            OutputStream outputStream = accept.getOutputStream();

            outputStream.write((responsebody+"请求成功").getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();
            accept.close();
        }

    }
}

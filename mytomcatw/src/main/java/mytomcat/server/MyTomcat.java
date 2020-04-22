package mytomcat.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mytomcat.config.ServletConfig;
import mytomcat.config.ServletConfigMapping;
import mytomcat.request.MyRequest;
import mytomcat.response.MyResponse;
import mytomcat.serlvet.Servlet;
import mytomcat.utils.HttpUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/22 9:55
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MyTomcat {
    private int port;
    private Map<String,Class<Servlet>> servletMap =new HashMap<String,Class<Servlet>>();

    public MyTomcat(int port) {
        this.port = port;
    }

    public void start(){
       try {
            ServerSocket serverSocket = new ServerSocket(port);
           try {
               initServlet();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
           log.info("Tomcat startup on "+port);
            while(true){
                Socket socket = serverSocket.accept();
                MyRequest myRequest = new MyRequest(socket.getInputStream());
                MyResponse myResponse = new MyResponse(socket.getOutputStream());
                if("/".equals(myRequest.getUrl())){
                    myResponse.wirte(HttpUtil.getHttpResponseContext404());
                }else if(servletMap.get(myRequest.getUrl())==null){
                    myResponse.wirteHtml(myRequest.getUrl());
                }else{
                    dispacth(myRequest,myResponse);
                }
            /*    String url = myRequest.getUrl();
                myResponse.wirteHtml(url);*/
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dispacth(MyRequest request,MyResponse response){
        Class<Servlet> servletClass = servletMap.get(request.getUrl());
        Servlet servlet = null;
        try {
            servlet = servletClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(servlet!=null){
            try {
                servlet.service(request,response);
            }catch (Exception e){
                response.wirte(HttpUtil.getHttpResponseContext500(e));
                e.printStackTrace();
            }

        }else{
            response.wirte(HttpUtil.getHttpResponseContext404());
        }
    }
    
    private void initServlet() throws ClassNotFoundException {
        log.info("初始化servlet配置，代替xml");
        List<ServletConfig> config = ServletConfigMapping.config;
        for (ServletConfig servletConfig : config) {
            servletMap.put(servletConfig.getUrlMapping(),(Class<Servlet>)Class.forName(servletConfig.getClazz()));
        }
        
    }



}

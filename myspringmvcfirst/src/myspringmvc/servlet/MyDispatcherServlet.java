package myspringmvc.servlet;

import myspringmvc.annotation.MyController;
import myspringmvc.annotation.MyQualifier;
import myspringmvc.annotation.MyRequestMapping;
import myspringmvc.annotation.MyService;
import myspringmvc.handler.HandlerService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 16:34
 * @Version V1.0
 */
public class MyDispatcherServlet extends HttpServlet {

    public MyDispatcherServlet() {

    }
    List<String> classNames = new ArrayList<>();
    Map<String,Object> beans = new HashMap<>();
    Map<String,Object> handMap = new HashMap<>();
    Map<String,Map<String,Object>> methodMap = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //拿到的地址
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String contextPath1 = requestURI.replaceFirst(contextPath,"");
        Method method = (Method)handMap.get(contextPath1);
        Map<String, Object> stringObjectMap = methodMap.get(contextPath1);
        String methodStr = (String)stringObjectMap.keySet().toArray()[0];
        String controller = contextPath1.replace(methodStr, "");
        Object o = beans.get(controller);
        HandlerService handlerService = (HandlerService)beans.get("handlerServiceImpl");
        Object[] args = handlerService.hand(req, resp, method, beans);
        try {
            Object invoke = method.invoke(o, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //扫描哪些类需要实例化 class
        doScanPackage("myspringmvc");
        for (String name:classNames) {
            System.out.println(name);
        }
        doInstance();
        //依赖注入
        iocDi();
        //url 和方法映射
        MyHandlerMapper();
        Set<Map.Entry<String, Object>> entries = handMap.entrySet();
        for(Map.Entry<String, Object> obj:entries){
            String key = obj.getKey();
            Object value = obj.getValue();
            System.out.println(key+"------"+value);
        }

        super.init(config);
    }

    private void MyHandlerMapper() {
        if(beans.isEmpty()){
            return;
        }
        Set<Map.Entry<String, Object>> entries = beans.entrySet();

        for(Map.Entry<String, Object> item:entries){
            Object instance = item.getValue();
            Class<?> aClass = instance.getClass();
            if(aClass.isAnnotationPresent(MyController.class)){
                MyRequestMapping annotation = aClass.getAnnotation(MyRequestMapping.class);
                String value = annotation.value();
                Method[] methods = aClass.getMethods();
                for(Method method:methods){
                    if(method.isAnnotationPresent(MyRequestMapping.class)){
                        MyRequestMapping annotation1 = method.getAnnotation(MyRequestMapping.class);
                        String methodValue = annotation1.value();
                        HashMap<String, Object> strHashMap = new HashMap<>();
                        strHashMap.put(methodValue,method);
                        methodMap.put(value+methodValue,strHashMap);
                        handMap.put(value+methodValue,method);
                    }

                }

            }

        }
    }

    private void iocDi() {
        if(beans.isEmpty()){
            System.out.println("没有实例化bean");
            return;
        }
        //把service 注入
        Set<Map.Entry<String, Object>> entries = beans.entrySet();

        for(Map.Entry<String, Object> obj:entries){
            Object instance = obj.getValue();
            Class<?> aClass = instance.getClass();
            if(aClass.isAnnotationPresent(MyController.class)){
                Field[] declaredFields = aClass.getDeclaredFields();
                for(Field item:declaredFields){
                    if(item.isAnnotationPresent(MyQualifier.class)){
                        MyQualifier annotation = item.getAnnotation(MyQualifier.class);
                        String value = annotation.value();
                        if(beans.containsKey(value)){
                            item.setAccessible(true);//放开权限
                            try {
                                item.set(instance,beans.get(value));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }else{
                            System.out.println("没对应的值");
                            continue;
                        }
                    }
                }
            }

        }
    }

    private void doInstance() {
        if(classNames.size()<=0){
            System.out.println("扫描失败");
            return;
        }
        //遍历扫描到的calss全类名路径
        for (String name:classNames){
            name= name.replaceAll(".class", "");
            try {
                Class<?> aClass = Class.forName(name);
                if(aClass.isAnnotationPresent(MyController.class)){
                    MyController annotation = aClass.getAnnotation(MyController.class);
                    Object instance = aClass.newInstance();

                    MyRequestMapping myRequestMapping= aClass.getAnnotation(MyRequestMapping.class);
                    String value = myRequestMapping.value();
                    beans.put(value,instance);

                }else if(aClass.isAnnotationPresent(MyService.class)){
                    MyService annotation = aClass.getAnnotation(MyService.class);
                    Object instance = aClass.newInstance();
                    beans.put(annotation.value(),instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //扫描class类
    private void doScanPackage(String basePackage) {
        //扫描编译哈的项目下的所有类
        URL url = this.getClass().getClassLoader().getResource("/" + basePackage.replaceAll("\\.", "/"));
        String fileStr = url.getFile();
        File file = new File(fileStr);
        String[] files = file.list();
        for (String path:files){
            File filePath = new File(fileStr + path);
            if(filePath.isDirectory()){
                doScanPackage(basePackage+"."+path);
            }else{
                classNames.add(basePackage+"."+filePath.getName());
            }
        }


    }
}

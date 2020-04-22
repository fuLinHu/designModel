package myspringmvc.controller;



import myspringmvc.annotation.MyController;
import myspringmvc.annotation.MyQualifier;
import myspringmvc.annotation.MyRequestMapping;
import myspringmvc.annotation.MyRequestParam;
import myspringmvc.serrvice.FlhService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/21 16:17
 * @Version V1.0
 */
@MyController
@MyRequestMapping("/myspringmvc")
public class FlhController {

    @MyQualifier("flhservice1")
    FlhService flhService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest re, HttpServletResponse res, @MyRequestParam("age") String age, @MyRequestParam("name") String name){
        String query = flhService.query(age, name);
        PrintWriter writer = null;
        try {
            writer = res.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(query);
    }

}

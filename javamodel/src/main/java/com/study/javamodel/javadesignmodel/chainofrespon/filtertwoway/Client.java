package com.study.javamodel.javadesignmodel.chainofrespon.filtertwoway;

import com.study.javamodel.javadesignmodel.chainofrespon.filter.Request;
import com.study.javamodel.javadesignmodel.chainofrespon.filter.Response;



/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:56
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        ChainFilter chainFilter = new TigerChianFilter();
        chainFilter.addFilter(new A1()).addFilter(new A2()).addFilter(new A3()).addFilter(new A4());
        Request request = new Request();
        Response response = new Response();
        chainFilter.doFilter(request,response);
    }
}

package com.study.javamodel.javadesignmodel.chainofrespon.filter;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:56
 * @Version V1.0
 */
public class Client {
    @Resource
    private List<Filter> Filter;

    public static void main(String[] args) {

        ChainFilter chainFilter = new ChainFilter();
        ChainFilter chainFilter1 = chainFilter.addFlter(new A1()).addFlter(new A2()).addFlter(new A3());
        List<Filter> filters = chainFilter1.getFilters();
        Request request = new Request();
        Response response = new Response();
        for (Filter filter : filters) {
            if(!filter.doFilter(request,response)){
                return;
            }

        }
    }
}

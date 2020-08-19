package com.study.javamodel.javadesignmodel.chainofrespon.filtertwoway;

import com.study.javamodel.javadesignmodel.chainofrespon.filter.Request;
import com.study.javamodel.javadesignmodel.chainofrespon.filter.Response;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/6 9:21
 * @Version V1.0
 */
public class TigerChianFilter implements ChainFilter {
    List<Filter> filters = new ArrayList<>();
    private int start = 0;
    private int now = 0;
    @Override
    public void doFilter(Request request, Response response) {
        if(start<filters.size()&&now < filters.size()){
            if(now<filters.size()){
                Filter filter = filters.get(now);
                now++;
                filter.doFilter(request,response,this);
            }
            if(now == filters.size()){
                if(start==0){
                    Collections.reverse(filters);
                }
                Filter filter = filters.get(start);
                start++;
                filter.doFilter(request,response,this);
            }
        }
    }

    @Override
    public  ChainFilter addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }


}

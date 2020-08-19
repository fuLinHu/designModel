package com.study.javamodel.javadesignmodel.chainofrespon.filtertwoway;

import com.study.javamodel.javadesignmodel.chainofrespon.filter.Request;
import com.study.javamodel.javadesignmodel.chainofrespon.filter.Response;


/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:53
 * @Version V1.0
 */
public class A2 implements Filter {
    @Override
    public boolean doFilter(Request request, Response response,ChainFilter chainFilter) {
        System.out.println(this.getClass().getName());
        chainFilter.doFilter(request,response);
        return true;
    }

    @Override
    public Integer order() {
        return 4;
    }
}

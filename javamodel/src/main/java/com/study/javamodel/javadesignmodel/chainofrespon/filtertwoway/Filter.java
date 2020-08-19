package com.study.javamodel.javadesignmodel.chainofrespon.filtertwoway;

import com.study.javamodel.javadesignmodel.chainofrespon.filter.Request;
import com.study.javamodel.javadesignmodel.chainofrespon.filter.Response;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:51
 * @Version V1.0
 */
public interface Filter {
    public boolean doFilter(Request request, Response response,ChainFilter chainFilter);
    public Integer order();
}

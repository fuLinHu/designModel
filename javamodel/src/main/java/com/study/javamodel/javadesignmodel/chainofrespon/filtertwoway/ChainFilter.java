package com.study.javamodel.javadesignmodel.chainofrespon.filtertwoway;

import com.study.javamodel.javadesignmodel.chainofrespon.filter.Request;
import com.study.javamodel.javadesignmodel.chainofrespon.filter.Response;


/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:54
 * @Version V1.0
 */
public interface ChainFilter  {
    void doFilter(Request request, Response response);
    ChainFilter addFilter(Filter filter);

}

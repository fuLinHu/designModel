package com.study.javamodel.javadesignmodel.chainofrespon.filter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:51
 * @Version V1.0
 */
public interface Filter {
    public boolean doFilter(Request request,Response response);
    public Integer order();
}

package com.study.javamodel.javadesignmodel.chainofrespon.filter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:53
 * @Version V1.0
 */
public class A2 implements Filter {
    @Override
    public boolean doFilter(Request request, Response response) {
        System.out.println(this.getClass().getName());
        return false;
    }

    @Override
    public Integer order() {
        return 4;
    }
}

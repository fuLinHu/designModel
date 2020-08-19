package com.study.javamodel.javadesignmodel.chainofrespon.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/5 19:54
 * @Version V1.0
 */
public class ChainFilter implements Filter{
    private List<Filter> filters = new ArrayList<>();
    @Override
    public boolean doFilter(Request request, Response response) {
        return false;
    }

    @Override
    public Integer order() {
        return 5;
    }

    public ChainFilter addFlter(Filter filter){
        filters.add(filter);
        return this;
    }

    public List<Filter> getFilters() {
        return filters;
    }
}

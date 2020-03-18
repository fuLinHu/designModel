package com.study.javamodel.javadesignmodel.chainofrespon;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 11:42
 * @Version V1.0
 */
public abstract  class  Handler {
    private Handler nextHandler;

    //获取下一个 责任链对象
    public Handler getNextHandler() {
        return nextHandler;
    }

    //设置下一个责任对象
    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }


    public abstract void doFilter(Integer day);

}

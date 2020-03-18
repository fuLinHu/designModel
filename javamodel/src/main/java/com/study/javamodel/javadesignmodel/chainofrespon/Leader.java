package com.study.javamodel.javadesignmodel.chainofrespon;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 13:54
 * @Version V1.0
 */
public class Leader extends Handler {
    @Override
    public void doFilter(Integer day) {
        if(day>10&&day<30){
            System.out.println("你请假天数【"+day+"】Leader 批准，你可以请假！！");
        }else{
            System.out.println("你请假天数【"+day+"】Leader 没权限批准，你可以找下一个领导请假！！");
            if(getNextHandler()!=null){
                getNextHandler().doFilter(day);
            }
        }
    }
}

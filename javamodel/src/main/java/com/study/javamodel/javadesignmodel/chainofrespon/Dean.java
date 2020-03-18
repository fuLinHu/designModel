package com.study.javamodel.javadesignmodel.chainofrespon;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 14:05
 * @Version V1.0
 */
public class Dean extends Handler {
    @Override
    public void doFilter(Integer day) {
        if(day>=5&&day<10){
            System.out.println("你请假天数【"+day+"】Dean批准，你可以请假！！");
        }else{
            System.out.println("你请假天数【"+day+"】Dean 没权限批准，你可以找下一个领导请假！！");
            if(getNextHandler()!=null){
                getNextHandler().doFilter(day);
            }
        }
    }
}

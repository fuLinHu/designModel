package com.study.javamodel.javadesignmodel.chainofrespon;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/18 13:55
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) {

        Handler classaBoy = new ClassaBoy();
        Handler teacher = new Teacher();
        Dean dean = new Dean();
        Handler leader = new Leader();

        classaBoy.setNextHandler(teacher);
        teacher.setNextHandler(dean);
        dean.setNextHandler(leader);

        classaBoy.doFilter(8);

    }
}

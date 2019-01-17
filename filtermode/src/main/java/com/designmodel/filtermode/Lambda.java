package com.designmodel.filtermode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lambda {
    public static void main(String[] arg){

        List list= new ArrayList<Object>();
        Test t=new Test();
        t.setAge(10);
        t.setName("fulinhu");
        t.setStartTime(new Date());
        Test t1=new Test();
        t1.setAge(11);
        t1.setName("fulinhu1");
        t1.setStartTime(new Date());
        Test t2=new Test();
        t2.setAge(10);
        t2.setName("fulinhu2");
        t2.setStartTime(new Date());
        Test t3=new Test();
        t3.setAge(12);
        t3.setName("fulinhu3");
        t3.setStartTime(new Date());
        Test t4=new Test();
        t4.setAge(13);
        t4.setName("fulinhu4");
        t4.setStartTime(new Date());
        Test t5=new Test();
        t5.setAge(15);
        t5.setName("fulinhu5");
        t5.setStartTime(new Date());
        list.add(t);
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.forEach(o->{
            System.out.println(o);
        });
        list.stream().filter(e->{
            e=(Test)e;
            return ((Test) e).getAge()>10;
        }).forEach(tt->{
            tt=(Test)tt;
            System.out.println(((Test) tt).getName());
        });
    }

}

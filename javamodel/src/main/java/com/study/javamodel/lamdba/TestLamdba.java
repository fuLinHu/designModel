package com.study.javamodel.lamdba;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/9 11:28
 * @Version V1.0
 */
public class TestLamdba {
    public static void main(String[] args) {
        addnoparam add=()->4;
        int add1 = add.add();
        System.out.println("addnoparam----"+add1);

        addnoparamandvoid addnoparamandvoid = ()-> System.out.println("没有任何参数和返回值");
        addnoparamandvoid.add();

        addhaveoneparamandvoid addhaveoneparamandvoid = (a)->System.out.println("没有返回值,但是有一个参数"+a);
        addhaveoneparamandvoid = a->System.out.println("没有返回值,但是有一个参数"+a);
        addhaveoneparamandvoid.add(4);

        addhavetwoparamandvoid addhavetwoparamandvoid = (a,b)->System.out.println("没有返回值,但是有一个参数"+(a+b));
        addhavetwoparamandvoid.add(2,5);


        addhavethreeparamandvoid addhavethreeparamandvoid = (a,b,c)->System.out.println("没有返回值,但是有一个参数"+(a+b+c));
        addhavethreeparamandvoid.add(1,2,3);

        addhavethreeparam addhavethreeparam = (a,b,c)->{
            return (int) (a+b+c);
        };
        int add2 = addhavethreeparam.add(3, 4, 5);
        System.out.println("最后一个：：："+add2);


    }
}
//如果用lamdba表达式  必须是接口只有一个方法要被实现
interface addnoparam{
    public int add();
}
interface addnoparamandvoid{
    public void add();
}

interface addhaveoneparamandvoid{
    public void add(int a);
}
interface addhavetwoparamandvoid{
    public void add(int a,double b);
}
interface addhavethreeparamandvoid{
    public void add(int a,double b,float c);
}
interface addhavethreeparam{
    public int add(int a,double b,float c);
}
package com.study.javamodel.jvm.jvmdetail;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/3/26 10:43
 * @Version V1.0
 */
public class MetaTest {
    /*private static Byte[] by=null;*/
    public static void main(String[] args) throws ClassNotFoundException {
        for (int i = 0; i < 1000000000; i++) {
            ClassLoader.getSystemClassLoader().loadClass("com.study.javamodel.javadesignmodel.template.XiaoHong");
            Byte[] by =new Byte[102400];
            if(i>10000000){
                by =new Byte[1024000000];
            }
            /*by =new Byte[1024];
            String s = "asdfasl fjas fask;fma;sfas; fasl; fas; "+i;*/
        }
    }
}

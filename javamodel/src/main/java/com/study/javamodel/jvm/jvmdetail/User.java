package com.study.javamodel.jvm.jvmdetail;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 8:44
 * @Version V1.0
 */
public class User {
    public static void main(java.lang.String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //systemClassLoader.loadClass("com.study.javamodel.jvm.jvmdetail.User");
        ClassLoader parentExt = systemClassLoader.getParent();
        ClassLoader parent = parentExt.getParent();
        System.out.println("systemClassLoader:"+systemClassLoader);
        System.out.println("parentExt:"+parentExt);
        System.out.println("parent:"+parent);

        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        System.out.println("------------bootstrap-----------------------");
        for (URL urL : urLs) {
            System.out.println(urL.toString());
        }
        System.out.println("------------bootstrap-----------------------");
        System.out.println("extClassloader加载以下文件：");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println();
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));

    }
}

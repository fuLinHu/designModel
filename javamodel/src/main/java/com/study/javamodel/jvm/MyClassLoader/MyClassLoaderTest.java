package com.study.javamodel.jvm.MyClassLoader;

import java.io.FileInputStream;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 9:36
 * @Version V1.0
 */
public class MyClassLoaderTest{



    public static class MyClassLoader extends ClassLoader {
        private String  classPath;
        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }
        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name  + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }


        //实现双亲委派
        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] data =null;
            try {
                data = loadByte(name);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return defineClass(name, data, 0, data.length);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader myClassLoader = new MyClassLoader("E:\\data");
        Class<?> aClass = myClassLoader.loadClass("com.study.javamodel.jvm.MyClassLoader.TestMyLoad");
        System.out.println(aClass.getClassLoader());
//        Class<?> aClass = myClassLoader.findClass("com.study.javamodel.jvm.MyClassLoader.TestMyLoad");
//        Object instance = aClass.newInstance();
//        TestMyLoad testMyLoad = new TestMyLoad();
//        ClassLoader classLoader = testMyLoad.getClass().getClassLoader();
//        System.out.println(instance instanceof TestMyLoad);
//        System.out.println(instance);
//        System.out.println(aClass.getClassLoader());
//        System.out.println(classLoader);
    }
}

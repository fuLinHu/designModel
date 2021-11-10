package com.study.javamodel.jvm.MyClassLoader;

import java.io.FileInputStream;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/11 9:36
 * @Version V1.0
 */
public class MyClassLoaderTestBreak {
    public static class MyClassLoader extends ClassLoader {
        private String classPath;
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

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();
                   /* try {
                        if (parent != null) {
                            c = parent.loadClass(name, false);
                        } else {
                            c = findBootstrapClassOrNull(name);
                        }
                    } catch (ClassNotFoundException e) {
                        // ClassNotFoundException thrown if class not found
                        // from the non-null parent class loader
                    }*/

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        if(name.startsWith("java")){
                            //双亲委派
                            c = this.getParent().loadClass(name);
                        }else{
                            //打破双亲委派
                            c = findClass(name);
                        }
                        long t1 = System.nanoTime();


                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
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
        MyClassLoader myClassLoader = new MyClassLoader("E:\\fliedata\\class");
        Class<?> aClass = myClassLoader.loadClass("com.study.javamodel.jvm.MyClassLoader.TestMyLoad");
        System.out.println(aClass.getClassLoader());

       aClass = myClassLoader.loadClass("sun.nio.ByteBuffered");
        System.out.println(aClass);

    }
}

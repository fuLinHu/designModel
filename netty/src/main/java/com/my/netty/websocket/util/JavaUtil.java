package com.my.netty.websocket.util;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/11/3 14:59
 * @Version V1.0
 */
public class JavaUtil {

    public static boolean isBaseType(Class className) {
        return  className.equals(String.class)||
                className.equals(Integer.class) ||
                className.equals(int.class) ||
                className.equals(Byte.class) ||
                className.equals(byte.class) ||
                className.equals(Long.class) ||
                className.equals(long.class) ||
                className.equals(Double.class) ||
                className.equals(double.class) ||
                className.equals(Float.class) ||
                className.equals(float.class) ||
                className.equals(Character.class) ||
                className.equals(char.class) ||
                className.equals(Short.class) ||
                className.equals(short.class) ||
                className.equals(Boolean.class) ||
                className.equals(boolean.class);
    }
}

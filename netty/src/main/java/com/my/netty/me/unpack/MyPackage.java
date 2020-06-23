package com.my.netty.me.unpack;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/4 8:52
 * @Version V1.0
 */

public class MyPackage {
    private String message;
    private int len;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    @Override
    public String toString() {
        return "MyPackage{" +
                "message='" + message + '\'' +
                ", len=" + len +
                '}';
    }
}

package com.my.netty.websocket.entity;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/19 20:50
 * @Version V1.0
 */

public enum ResultStatus {
    SUCCESS("200","成功"),
    ERROR("500","系统错误"),
    NOTFOUND("404","没找到数据");

    private  String status;
    private String message;
    ResultStatus(String status, String message){
        this.message = message;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }

}

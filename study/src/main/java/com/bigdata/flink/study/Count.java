package com.bigdata.flink.study;

import io.netty.handler.codec.mqtt.MqttMessageIdAndPropertiesVariableHeader;
import lombok.Data;
import sun.applet.Main;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/30 15:12
 * @Version V1.0
 */
@Data
public class Count {
    private String key;
    private Integer count;

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date.getTime());
    }

}

package com.spring.module.gateway.predicatesfactory;

import lombok.Data;

import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/6/19 8:28
 * @Version V1.0
 */
@Data
public class TigerBetweenTimeConfig {
    private LocalTime startTime;
    private LocalTime endTime;

    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now());
    }

}

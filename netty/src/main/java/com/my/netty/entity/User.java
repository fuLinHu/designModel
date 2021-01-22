package com.my.netty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/21 10:21
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer age;
    private String name;
    private Date birthDay;

}

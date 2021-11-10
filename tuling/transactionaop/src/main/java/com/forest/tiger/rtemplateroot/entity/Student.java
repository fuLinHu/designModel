package com.forest.tiger.rtemplateroot.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/8/10 14:28
 * @Param $
 * @return $
 * @Version V1.0
 */

@Data
@TableName(value = "student")
public class Student {

    @TableId(type = IdType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String name;
    private Integer age;
    private Date birthday;
}

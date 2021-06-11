package com.luban.productserver.entity;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/6/8 11:09
 * @Version V1.0
 */

public class Production {
    private int id;
    private String name;
    private Date create;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}

package com.mystarter.myjsonspringbootstarter.service;

import com.alibaba.fastjson.JSON;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/4/23 10:48
 * @Version V1.0
 */
public class MyJsonService {
    private String name;

    /**
     * 使用 fastjson 将对象转换为 json 字符串输出
     *
     * @param object 传入的对象
     * @return 输出的字符串
     */
    public String objToJson(Object object) {
        return getName() + JSON.toJSONString(object);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

package com.elk.demo.searchentity.enumentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 9:25
 * @Version V1.0
 */

@Getter
public enum  FieldType {
    TERMS(1,"terms"),
    PREFIX(2,"prefix"),
    REGEXP(3,"regexp"),
    WILDCARD(4,"wildcard");

    private Integer code;
    private String type;
    FieldType(Integer code,String type){
        this.code=code;
        this.type = type;
    }


}

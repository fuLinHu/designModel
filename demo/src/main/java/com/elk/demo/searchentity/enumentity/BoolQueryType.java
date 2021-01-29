package com.elk.demo.searchentity.enumentity;

import lombok.Getter;

@Getter
public enum BoolQueryType {
    SHOULD(1,"should"),
    MUST(2,"must"),
    MUST_NOT(3,"must_not"),
    FILTER(4,"filter");

    private Integer code;
    private String type;
    BoolQueryType(Integer code,String type){
        this.code=code;
        this.type = type;
    }
}

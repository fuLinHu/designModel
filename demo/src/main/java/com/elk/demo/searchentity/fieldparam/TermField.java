package com.elk.demo.searchentity.fieldparam;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/27 9:52
 * @Version V1.0
 */
@Data
@SuperBuilder
public class TermField extends Field {
    //权重  权重越大  评分越高
    private Float boost;
    //字段类型  映射的时候是否为keyword 类型。
    //如果是keyword类型，则查询的时候  字段名称不需要添加 .keyword  否则需要添加
    private boolean fieldTypeIfKeyWord;
}

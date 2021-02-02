package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.enumentity.BoolQueryType;
import com.elk.demo.searchentity.enumentity.FieldType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/26 19:27
 * @Version V1.0
 */
@Data
@SuperBuilder
public class Field {
    //字段名称
    private String fieldName;
    //字段值
    private Object fieldValue;


    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/28 9:33
     * @Param
     * @Version V1.0
     * 查询类型
     * 如果  查询类型 继承了该类 则该字段不赋值
     * 否则需要给该字段赋值
     * {@link com.elk.demo.searchentity.enumentity.FieldType}
     **/
    private FieldType fieldType;
    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/28 10:14
     * @Param
     * @Version V1.0
     * 查询类型，当为null的时候是基本查询，当不为空的时候是组合查询
     * {@link com.elk.demo.searchentity.enumentity.BoolQueryType}
     **/
    @Builder.Default
    private BoolQueryType boolQueryType = BoolQueryType.SHOULD;




}

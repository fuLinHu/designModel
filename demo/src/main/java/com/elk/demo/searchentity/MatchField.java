package com.elk.demo.searchentity;

import lombok.Builder;
import lombok.Data;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/26 15:48
 * @Version V1.0
 */
@Data
@Builder
public class MatchField {
    //字段名称
    private String fieldName;
    //字段值
    private Object fieldValue;
    //操作符  OR  AND 默认是OR
    private String operator;


}

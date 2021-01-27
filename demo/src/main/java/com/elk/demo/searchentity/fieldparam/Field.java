package com.elk.demo.searchentity.fieldparam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
}

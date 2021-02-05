package com.elk.demo.searchentity.fieldparam.searchbasefield;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/5 10:18
 * @Version V1.0
 */
@Data
@SuperBuilder
public class SearchField extends Field {
    //字段名称
    private String fieldName;
    //字段值
    private Object fieldValue;

}

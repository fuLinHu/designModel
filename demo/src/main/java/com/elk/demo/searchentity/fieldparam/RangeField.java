package com.elk.demo.searchentity.fieldparam;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/27 10:31
 * @Version V1.0
 */
@Data
@SuperBuilder
public class RangeField extends Field {
    private Object lt;
    private Object lte;
    private Object gt;
    private Object gte;
    private Object from;
    private Object to;

    private Float boost;

}

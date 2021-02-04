package com.elk.demo.searchentity.agg;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.search.aggregations.support.ValueType;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/3 9:41
 * @Version V1.0
 */
@SuperBuilder
@Data
public class CardinalityAggField extends AggField {
    private ValueType valueType;
}

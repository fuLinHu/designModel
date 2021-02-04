package com.elk.demo.searchentity.agg;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/2 11:17
 * @Version V1.0
 */
@SuperBuilder
@Data
public class AggField {
    //字段名称
    private String fieldName;
    private String groupName;
    //子聚合
    private AggregationBuilder subAggregation;
}

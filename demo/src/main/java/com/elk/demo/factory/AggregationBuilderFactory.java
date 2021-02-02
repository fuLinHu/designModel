package com.elk.demo.factory;

import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.agg.AvgAggField;
import com.elk.demo.searchentity.fieldparam.Field;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/2 11:14
 * @Version V1.0
 */
public class AggregationBuilderFactory {

    public static AggregationBuilder getQueryBuilder(AggField aggField){
        AvgAggField avgAggField = (AvgAggField)aggField;
        AvgAggregationBuilder avgAggregationBuilder = new AvgAggregationBuilder(avgAggField.getFieldName());
        return avgAggregationBuilder;
    }
}

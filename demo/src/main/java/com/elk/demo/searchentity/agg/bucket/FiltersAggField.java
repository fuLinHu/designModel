package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/4 9:01
 * @Version V1.0
 */
@SuperBuilder
@Data
public class FiltersAggField extends AggField {

    private QueryBuilder[] filterQueryBuilders;
}



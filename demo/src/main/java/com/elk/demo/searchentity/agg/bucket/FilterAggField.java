package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/4 9:01
 * @Version V1.0
 */
@SuperBuilder
@Data
public class FilterAggField extends AggField {
    private QueryBuilder filterQueryBuilder;
}



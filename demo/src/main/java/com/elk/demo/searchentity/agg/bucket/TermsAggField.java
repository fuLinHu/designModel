package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.search.aggregations.support.ValueType;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/3 17:46
 * @Version V1.0
 */
@SuperBuilder
@Data
public class TermsAggField extends AggField {
    private ValueType valueType;
    //false  desc  true asc
    private Boolean countOrder;
    //false  desc  true asc
    private Boolean keyOrder;
    //显示桶得个数
    private Integer size;

    //这两个参数只作用到string类型
    //排除过滤 eg:"water_.*",
    private String executionHint;
    //包含过滤 eg:".*sport.*",
    private String include;
}

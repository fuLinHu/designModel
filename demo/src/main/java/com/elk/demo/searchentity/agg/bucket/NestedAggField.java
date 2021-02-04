package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/4 23:08
 * @Version V1.0
 */
@Data
@SuperBuilder
public class NestedAggField extends AggField {
    private String path;
}

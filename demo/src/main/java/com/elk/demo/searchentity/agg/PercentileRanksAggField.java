package com.elk.demo.searchentity.agg;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/3 10:26
 * @Version V1.0
 */
@Data
@SuperBuilder
public class PercentileRanksAggField extends AggField {
    private double[] values;
}

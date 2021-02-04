package com.elk.demo.searchentity.agg;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/3 10:41
 * @Version V1.0
 */
@SuperBuilder
@Data
public class GeoBoundsAggField extends AggField {
    private boolean wrapLongitude;
}

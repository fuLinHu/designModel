package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/5 11:17
 * @Version V1.0
 */
@Data
@SuperBuilder
public class IdsField extends Field {
    private String[] ids;
}

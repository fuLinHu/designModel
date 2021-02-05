package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.fieldparam.searchbasefield.SearchField;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/5 14:02
 * @Version V1.0
 */
@Data
@SuperBuilder
public class QueryStringField extends SearchField {
    private final Map<String,Float> fieldsMap = new HashMap<>();
    public String analyzer;
}

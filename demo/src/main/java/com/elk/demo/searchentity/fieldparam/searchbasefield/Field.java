package com.elk.demo.searchentity.fieldparam.searchbasefield;

import com.elk.demo.searchentity.enumentity.BoolQueryType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/26 19:27
 * @Version V1.0
 */
@Data
@SuperBuilder
public class  Field {
    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/28 10:14
     * @Param
     * @Version V1.0
     * 查询类型，当为null的时候是基本查询，当不为空的时候是组合查询
     * {@link com.elk.demo.searchentity.enumentity.BoolQueryType}
     **/
    @Builder.Default
    private BoolQueryType boolQueryType = BoolQueryType.SHOULD;

    //权重
    private Float boost;

    //字段名称
    private String fieldName;
}

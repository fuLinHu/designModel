package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.SearchParam;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 14:28
 * @Version V1.0
 */
@Data
@SuperBuilder
public class MultiMatchField extends MatchField {
    private String[] fieldNames;
    
    /**
     * @Author 付林虎
     * @Description //TODO  multi_match多字段匹配的三种类型，分别是best_fields（最佳字段） 、 most_fields（多数字段） 和 cross_fields（跨字段）
     * @Date 2021/1/28 14:39
     * @Param 
     * @Version V1.0
     * @return 
     **/
    private MultiMatchQueryBuilder.Type type;

    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/28 14:46
     * @Param
     * @Version V1.0
     * @同 {@link SearchParam#tie_breaker}
     **/
    private Float tie_breaker;
}

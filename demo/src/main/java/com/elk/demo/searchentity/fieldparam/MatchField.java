package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.fieldparam.searchbasefield.SearchField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.index.query.Operator;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/26 15:48
 * @Version V1.0
 */
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MatchField extends SearchField {
    //操作符  OR  AND 默认是OR
    private Operator operator;
    //默认值是 false ， 表示用来在查询时如果数据类型不匹配且无法转换时会报错。如果设置成 true 会忽略错误。
    //例如， 例子中的 age 是 integer 类型的，如果查询 age=xxy ，就会导致无法转换而报错。
    private Boolean lenient;
    /**
     * @Author 付林虎
     * @Description //TODO  简单理解 "all" --- 停用词失效，全查， none ---  停用词 不被查到
     * @Param
     * @Version V1.0
     * @return
     **/
    private String zero_terms_query;
    /**
     {
         "query": {
                 "match": {
                     "title": {
                         "query": "java elasticsearch spark hadoop",
                         "minimum_should_match": "75%"  或者是  3
                     }
             }
         }
     }
     最少匹配 同 {@link com.elk.demo.searchentity.SearchParam}
     这个只支持  string
     **/
    private String minimum_should_match;

}

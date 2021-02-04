package com.elk.demo.searchentity.result;

import lombok.Data;
import org.elasticsearch.search.aggregations.Aggregation;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/3 16:11
 * @Version V1.0
 */
@Data
public class AggResult {
    //结果
    private List<Aggregation> aggregationList;
    //结果Aggregation 对应得实现类 名称
    private String implClassName;
    //分组得组名称
    private String groupName;

    @Override
    public String toString() {
        return "{" +
                "\"aggregationList\":\"" + aggregationList +
                "\", \"implClassName\":\"" + implClassName + '\"' +
                ",\" groupName\":\"" + groupName + '\"' +
                '}';
    }
}

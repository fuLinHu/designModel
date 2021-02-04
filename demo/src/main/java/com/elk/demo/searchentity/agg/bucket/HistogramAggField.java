package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.search.aggregations.BucketOrder;

/**
 * @className
 * @Description TODO field  必须是数字类型
 * @Author 付林虎
 * @Date 2021/2/4 18:56
 * @Version V1.0
 */
@SuperBuilder
@Data
public class HistogramAggField extends AggField {

    private Integer interval; ////分桶间距
    private Boolean keyed; // hash结构返回，默认以数组形式返回每一个桶
    private Integer missing; //配置缺省默认值 todo 这个是干什么用得
    private Long minDocCount; //最少文档数桶过滤，只有不少于这么多文档的桶才会返回
    //false  desc  true asc
    private Boolean countOrder; //对桶排序，如果 histogram 聚合有一个权值聚合类型的"直接"子聚合，那么排序可以使用子聚合中的结果
    //false  desc  true asc
    private Boolean keyOrder; //对桶排序，如果 histogram 聚合有一个权值聚合类型的"直接"子聚合，那么排序可以使用子聚合中的结果
    private Integer offset; // 修改起始边界 根据公式  bucket_key = Math.floor((value - offset) / interval) * interval + offset


    private Section section; //范围扩展
    @Data
    public static class Section{
        //上限
        private Double from;
        //下限
        private Double to;

        public Section(Double from,Double to){
            this.from = from;
            this.to = to;
        }
    }
}
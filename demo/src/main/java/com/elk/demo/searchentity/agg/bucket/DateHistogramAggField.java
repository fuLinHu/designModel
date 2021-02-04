package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;

/**
 * @className
 * @Description TODO bucket_key = Math.floor(value / interval) * interval
 * @Author 付林虎
 * @Date 2021/2/4 21:33
 * @Version V1.0
 */
@SuperBuilder
@Data
public class DateHistogramAggField extends AggField {
    private Boolean keyed; // hash结构返回，默认以数组形式返回每一个桶
    //  Interval
    private Interval interval;

    //基于字段数据的单个存储桶聚合，
    // 可在当前文档集上下文中创建所有缺少字段值（实际上是缺少字段或配置了NULL值）的所有文档的存储桶。
    // 该聚合器通常会与其他字段数据存储桶聚合器（例如范围）结合使用，
    // 以返回由于缺少字段数据值而无法放置在任何其他存储桶中的所有文档的信息。
    private Integer  missing; //配置缺省默认值 todo 这个是干什么用得
    private Long minDocCount; //最少文档数桶过滤，只有不少于这么多文档的桶才会返回
    //false  desc  true asc
    private Boolean countOrder; //对桶排序，如果 histogram 聚合有一个权值聚合类型的"直接"子聚合，那么排序可以使用子聚合中的结果
    //false  desc  true asc
    private Boolean keyOrder; //对桶排序，如果 histogram 聚合有一个权值聚合类型的"直接"子聚合，那么排序可以使用子聚合中的结果

    //两者取其一
    private Long offset; // 修改起始边界 根据公式  bucket_key = Math.floor((value - offset) / interval) * interval + offset
    private String offsetStr;
    //日期格式化
    private String format;

    private Section section; //范围扩展
    @Data
    public static class Section{
        //上限
        private Long from;
        //下限
        private Long to;

        private String fromStr;

        private String toStr;

        public Section(Long from,Long to){
            this.from = from;
            this.to = to;
        }
        public Section(String fromStr,String toStr){
            this.fromStr = fromStr;
            this.toStr = toStr;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Interval {
        private DateHistogramInterval dateHistogramInterval;
        private Boolean ifFixed;
    }


}

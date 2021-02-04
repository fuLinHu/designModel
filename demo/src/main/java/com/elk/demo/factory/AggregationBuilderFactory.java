package com.elk.demo.factory;

import com.elk.demo.searchentity.agg.*;
import com.elk.demo.searchentity.agg.bucket.*;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.BucketOrder;
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.histogram.ExtendedBounds;
import org.elasticsearch.search.aggregations.bucket.histogram.HistogramAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.DateRangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.IncludeExclude;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/2 11:14
 * @Version V1.0
 */
public class AggregationBuilderFactory {

    private static AvgAggregationBuilder avgAggregationBuilder(AggField aggField){
        AvgAggField field = (AvgAggField)aggField;
        AvgAggregationBuilder builder = new AvgAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static MaxAggregationBuilder maxAggregationBuilder(AggField aggField){
        MaxAggField field = (MaxAggField)aggField;
        MaxAggregationBuilder builder = new MaxAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static MinAggregationBuilder minAggregationBuilder(AggField aggField){
        MinAggField field = (MinAggField)aggField;
        MinAggregationBuilder builder = new MinAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static SumAggregationBuilder sumAggregationBuilder(AggField aggField){
        SumAggField field = (SumAggField)aggField;
        SumAggregationBuilder builder = new SumAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static ValueCountAggregationBuilder valueCountAggregationBuilder(AggField aggField){
        ValueCountField field = (ValueCountField)aggField;
        ValueCountAggregationBuilder builder = new ValueCountAggregationBuilder(field.getGroupName(),field.getValueType());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    //Cardinality Aggregation，基数聚合。它属于multi-value，基于文档的某个值（可以是特定的字段，也可以通过脚本计算而来），计算文档非重复的个数（去重计数），相当于sql中的distinct。
    private static CardinalityAggregationBuilder cardinalityAggregationBuilder(AggField aggField){
        CardinalityAggField field = (CardinalityAggField)aggField;
        CardinalityAggregationBuilder builder = new CardinalityAggregationBuilder(field.getGroupName(),field.getValueType());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    //1.4 统计聚合
    //Stats Aggregation，统计聚合。它属于multi-value，基于文档的某个值（可以是特定的数值型字段，也可以通过脚本计算而来），计算出一些统计信息（min、max、sum、count、avg5个值）。
    private static StatsAggregationBuilder statsAggregationBuilder(AggField aggField){
        StatsAggField field = (StatsAggField)aggField;
        StatsAggregationBuilder builder = new StatsAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    //1.5 拓展的统计聚合
    //Extended Stats Aggregation，扩展统计聚合。它属于multi-value，比stats多4个统计结果： 平方和、方差、标准差、平均值加/减两个标准差的区间
    private static ExtendedStatsAggregationBuilder extendedStatsAggregationBuilder(AggField aggField){
        ExtendedStatsAggField field = (ExtendedStatsAggField)aggField;
        ExtendedStatsAggregationBuilder builder = new ExtendedStatsAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    //Percentiles Aggregation，百分比聚合。它属于multi-value，对指定字段（脚本）的值按从小到大累计每个值对应的文档数的占比（占所有命中文档数的百分比），返回指定占比比例对应的值。默认返回[ 1, 5, 25, 50, 75, 95, 99 ]分位上的值。
    private static PercentilesAggregationBuilder percentilesAggregationBuilder(AggField aggField){
        PercentilesAggField field = (PercentilesAggField)aggField;
        PercentilesAggregationBuilder builder = new PercentilesAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    /**
     POST /bank/_search?size=0
     {
     "aggs": {
     "gge_perc_rank": {
     "percentile_ranks": {
     "field": "age",
     "values": [
     25,
     30
     ]
     }
     }
     }
     }
     {//返回
     ...
     "aggregations": {
     "gge_perc_rank": {
     "values": {   //年龄小于25的文档占比为26.1%，年龄小于30的文档占比为49.2%
     "25.0": 26.1,
     "30.0": 49.2
     }
     }
     }
     }
     //1.7 百分比排名聚合
     //Percentile Ranks Aggregation，统计年龄小于25和年龄小于30的文档的占比，这里需求可以使用。
     **/
    private static PercentileRanksAggregationBuilder percentileRanksAggregationBuilder(AggField aggField){
        PercentileRanksAggField field = (PercentileRanksAggField)aggField;
        PercentileRanksAggregationBuilder builder = new PercentileRanksAggregationBuilder(field.getGroupName(),field.getValues());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    /**
     1.8 Top Hits
     Top Hits Aggregation，最高匹配权值聚合。获取到每组前n条数据，相当于sql 中Top（group by 后取出前n条）。它跟踪聚合中相关性最高的文档，该聚合一般用做 sub-aggregation，以此来聚合每个桶中的最高匹配的文档，较为常用的统计。
     GET index/type/_search?search_type=count
     {
     "query": {
     "match_all": {}
     },
     "aggs": {
     "all_interests": {
     "terms": {
     "field": "zxw_id",
     "size": 100
     },
     "aggs": {
     "top_tag_hits": {
     "top_hits": {
     "size": 1    //返回的最大文档个数（default 3）
     }
     }
     }
     }
     }
     }
     **/
    private static TopHitsAggregationBuilder topHitsAggregationBuilder(AggField aggField){
        TopHitsAggField field = (TopHitsAggField)aggField;
        TopHitsAggregationBuilder builder = new TopHitsAggregationBuilder(field.getGroupName());
        builder.size(field.getSize());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    /**
     1.9 Geo Bounds Aggregation
     Geo Bounds Aggregation，地理边界聚合。基于文档的某个字段（geo-point类型字段），计算出该字段所有地理坐标点的边界（左上角/右下角坐标点）
     GET index/type/_search?search_type=count
     {
     "query": {
     "match_all": {}
     },
     "aggs": {
     "viewport": {
     "geo_bounds": {
     "field": "location",
     "wrap_longitude": true //是否允许地理边界与国际日界线存在重叠
     }
     }
     }
     }
     {//返回，
     ...
     "aggregations": {
     "viewport": {
     "bounds": {
     "top_left": { //这个矩形区域左上角坐标
     "lat": 80.45,
     "lon": -160.22
     },
     "bottom_right": {//这个矩形区域右下角坐标
     "lat": 40.65,
     "lon": 42.57
     }
     }
     }
     }
     }
     **/
    private static GeoBoundsAggregationBuilder geoBoundsAggregationBuilder(AggField aggField){
        GeoBoundsAggField field = (GeoBoundsAggField)aggField;
        GeoBoundsAggregationBuilder builder = new GeoBoundsAggregationBuilder(field.getGroupName());
        builder.wrapLongitude(field.isWrapLongitude());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    /**
     1.10 Geo Centroid Aggregation
     Geo Centroid Aggregation，地理重心聚合。基于文档的某个字段（geo-point类型字段），计算所有坐标的加权重心。
     GET index/type/_search?search_type=count
     {
     "query" : {
     "match" : { "crime" : "burglary" }
     },
     "aggs" : {
     "centroid" : {
     "geo_centroid" : {
     "field" : "location"
     }
     }
     }
     }
     {//输出
     ...
     "aggregations": {
     "centroid": {
     "location": {      //重心经纬度
     "lat": 80.45,
     "lon": -160.22
     }
     }
     }
     }
     **/
    private static GeoCentroidAggregationBuilder geoCentroidAggregationBuilder(AggField aggField){
        GeoCentroidAggField field = (GeoCentroidAggField)aggField;
        GeoCentroidAggregationBuilder builder = new GeoCentroidAggregationBuilder(field.getGroupName());
        builder.field(field.getFieldName());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    //==========================2. 桶聚合===Bucket Aggregations，桶聚合。=======================================================//
    /*它执行的是对文档分组的操作（与sql中的group by类似），把满足相关特性的文档分到一个桶里，即桶分，输出结果往往是一个个包含多个文档的桶（一个桶就是一个group）。

    它有一个关键字（field、script），以及一些桶分（分组）的判断条件。执行聚合操作时候，文档会判断每一个分组条件，如果满足某个，该文档就会被分为该组（fall in）。

    它不进行权值的计算，他们对文档根据聚合请求中提供的判断条件（比如：{"from":0,  "to":100}）来进行分组（桶分）。桶聚合还会额外返回每一个桶内文档的个数。

    它可以包含子聚合——sub-aggregations（权值聚合不能包含子聚合，可以作为子聚合），子聚合操作将会应用到由父聚合产生的每一个桶上。

    它根据聚合条件，可以只定义输出一个桶；也可以输出多个（multi-bucket）；还可以在根据聚合条件动态确定桶个数（比如：terms aggregation）。*/


    //Terms Aggregation，词聚合。基于某个field，该 field 内的每一个【唯一词元】为一个桶，并计算每个桶内文档个数。默认返回顺序是按照文档个数多少排序。
    // 它属于multi-bucket。当不返回所有 buckets 的情况（它size控制），文档个数可能不准确。
    //todo 排序问题后期在 研究
    private static TermsAggregationBuilder termsAggregationBuilder(AggField aggField){
        TermsAggField field = (TermsAggField)aggField;
        TermsAggregationBuilder builder = new TermsAggregationBuilder(field.getGroupName(),field.getValueType());
        builder.field(field.getFieldName());
        List<BucketOrder> orders = new ArrayList<>();
        if(field.getCountOrder()!=null)  orders.add(BucketOrder.count(field.getCountOrder()));
        if(field.getKeyOrder()!=null)  orders.add(BucketOrder.key(field.getKeyOrder()));
        if(orders.size()>0){
            builder.order(orders);
        }
        if(field.getSize()!=null) builder.size(field.getSize());

        if((field.getInclude()!=null&&!"".equals(field.getInclude()))||(field.getExecutionHint()!=null&&!"".equals(field.getExecutionHint()))){
            builder.includeExclude(new IncludeExclude(field.getInclude(),field.getExecutionHint()));
        }
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static FilterAggregationBuilder filterAggregationBuilder(AggField aggField){
        FilterAggField field = (FilterAggField)aggField;
        FilterAggregationBuilder filter = AggregationBuilders.filter(field.getGroupName(), field.getFilterQueryBuilder());
        if(field.getSubAggregation()!=null) filter.subAggregation(field.getSubAggregation());
        return filter;
    }

    private static FiltersAggregationBuilder filtersAggregationBuilder(AggField aggField){
        FiltersAggField field = (FiltersAggField)aggField;
        FiltersAggregationBuilder filter = AggregationBuilders.filters(field.getGroupName(), field.getFilterQueryBuilders());
        if(field.getSubAggregation()!=null) filter.subAggregation(field.getSubAggregation());
        return filter;
    }

    private static RangeAggregationBuilder rangeAggregationBuilder(AggField aggField){
        RangeAggField field = (RangeAggField)aggField;
        RangeAggregationBuilder builder = AggregationBuilders.range(field.getGroupName());
        builder.field(aggField.getFieldName());
        List<RangeAggField.Section> sections = field.getSections();
        if(sections!=null&&sections.size()>0){
            for (RangeAggField.Section section : sections) {
                Double upper = section.getFrom();
                Double lower = section.getTo();
                if(upper!=null&&lower!=null){
                    builder.addRange(upper,lower);
                }else if(upper!=null&&lower==null){
                    builder.addUnboundedFrom(upper);
                }else if(upper==null&&lower!=null){
                    builder.addUnboundedTo(lower);
                }
            }
        }
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static DateRangeAggregationBuilder dateRangeAggregationBuilder(AggField aggField){
        DateRangeAggField field = (DateRangeAggField)aggField;
        DateRangeAggregationBuilder builder = AggregationBuilders.dateRange(field.getGroupName());
        builder.field(aggField.getFieldName());
        List<DateRangeAggField.Section> sections = field.getSections();
        if(sections!=null&&sections.size()>0){
            for (DateRangeAggField.Section section : sections) {
                String upper = section.getFrom();
                String lower = section.getTo();
                if(upper!=null&&lower!=null){
                    builder.addRange(upper,lower);
                }else if(upper!=null&&lower==null){
                    builder.addUnboundedFrom(upper);
                }else if(upper==null&&lower!=null){
                    builder.addUnboundedTo(lower);
                }
            }
        }
        if(field.getFormat()!=null&&!"".equals(field.getFormat())) builder.format(field.getFormat());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }
    //bucket_key = Math.floor((value - offset) / interval) * interval + offset
    private static HistogramAggregationBuilder histogramAggregationBuilder(AggField aggField){
        HistogramAggField field = (HistogramAggField)aggField;
        HistogramAggregationBuilder builder = AggregationBuilders.histogram(field.getGroupName());
        builder.field(aggField.getFieldName());
        if(field.getInterval()!=null) builder.interval(field.getInterval());
        if(field.getKeyed()!=null) builder.keyed(field.getKeyed());
        if(field.getSection()!=null) builder.extendedBounds(field.getSection().getFrom(),field.getSection().getTo());
        if(field.getMissing()!=null) builder.missing(field.getMissing());
        if(field.getMinDocCount()!=null) builder.minDocCount(field.getMinDocCount());
        if(field.getOffset()!=null) builder.offset(field.getOffset());

        List<BucketOrder> orders = new ArrayList<>();
        if(field.getCountOrder()!=null)  orders.add(BucketOrder.count(field.getCountOrder()));
        if(field.getKeyOrder()!=null)  orders.add(BucketOrder.key(field.getKeyOrder()));
        if(orders.size()>0){
            builder.order(orders);
        }

        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    //bucket_key = Math.floor(value / interval) * interval
    private static DateHistogramAggregationBuilder dateHistogramAggregationBuilder(AggField aggField){
        DateHistogramAggField field = (DateHistogramAggField)aggField;
        DateHistogramAggregationBuilder builder = AggregationBuilders.dateHistogram(field.getGroupName());
        builder.field(aggField.getFieldName());

        if(field.getFormat()!=null&&!"".equals(field.getFormat())) builder.format(field.getFormat());
        if(field.getKeyed()!=null) builder.keyed(field.getKeyed());
        if(field.getInterval()!=null){
            Boolean ifFixed = field.getInterval().getIfFixed();
            if(ifFixed){
                builder.fixedInterval(field.getInterval().getDateHistogramInterval());
            }else{
                builder.calendarInterval(field.getInterval().getDateHistogramInterval());
            }
        }

        if(field.getSection()!=null) builder.extendedBounds(new ExtendedBounds(field.getSection().getFrom(),field.getSection().getTo()));
        if(field.getSection()!=null) builder.extendedBounds(new ExtendedBounds(field.getSection().getFromStr(),field.getSection().getToStr()));

        if(field.getMissing()!=null) builder.missing(field.getMissing());
        if(field.getMinDocCount()!=null) builder.minDocCount(field.getMinDocCount());
        if(field.getOffset()!=null) builder.offset(field.getOffset());
        if(field.getOffsetStr()!=null&&!"".equals(field.getOffsetStr())) builder.offset(field.getOffsetStr());

        List<BucketOrder> orders = new ArrayList<>();
        if(field.getCountOrder()!=null)  orders.add(BucketOrder.count(field.getCountOrder()));
        if(field.getKeyOrder()!=null)  orders.add(BucketOrder.key(field.getKeyOrder()));
        if(orders.size()>0){
            builder.order(orders);
        }

        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }

    private static NestedAggregationBuilder nestedAggregationBuilder(AggField aggField){
        NestedAggField field = (NestedAggField)aggField;
        NestedAggregationBuilder builder = AggregationBuilders.nested(field.getGroupName(),field.getPath());
        if(field.getSubAggregation()!=null) builder.subAggregation(field.getSubAggregation());
        return builder;
    }





    public static AggregationBuilder getQueryBuilder(AggField aggField){
        if(aggField instanceof AvgAggField){
            return avgAggregationBuilder(aggField);
        }else if(aggField instanceof MaxAggField){
            return maxAggregationBuilder(aggField);
        }else if(aggField instanceof MinAggField){
            return minAggregationBuilder(aggField);
        }else if(aggField instanceof SumAggField){
            return sumAggregationBuilder(aggField);
        }else if(aggField instanceof ValueCountField){
            return valueCountAggregationBuilder(aggField);
        }else if(aggField instanceof StatsAggField){
            return statsAggregationBuilder(aggField);
        }else if(aggField instanceof ExtendedStatsAggField){
            return extendedStatsAggregationBuilder(aggField);
        }else if(aggField instanceof PercentilesAggField){
            return percentilesAggregationBuilder(aggField);
        }else if(aggField instanceof PercentileRanksAggField){
            return percentileRanksAggregationBuilder(aggField);
        }else if(aggField instanceof TopHitsAggField){
            return topHitsAggregationBuilder(aggField);
        }else if(aggField instanceof GeoBoundsAggField){
            return geoBoundsAggregationBuilder(aggField);
        }else if(aggField instanceof GeoCentroidAggField){
            return geoCentroidAggregationBuilder(aggField);
        }else if(aggField instanceof TermsAggField ){
            return termsAggregationBuilder(aggField);
        }else if(aggField instanceof FilterAggField ){
            return filterAggregationBuilder(aggField);
        }else if(aggField instanceof FiltersAggField){
            return filtersAggregationBuilder(aggField);
        }else if(aggField instanceof RangeAggField){
            return rangeAggregationBuilder(aggField);
        }else if(aggField instanceof DateRangeAggField){
            return dateRangeAggregationBuilder(aggField);
        }else if(aggField instanceof HistogramAggField){
            return histogramAggregationBuilder(aggField);
        }else if(aggField instanceof DateHistogramAggField){
            return dateHistogramAggregationBuilder(aggField);
        }else if(aggField instanceof NestedAggField){
            return nestedAggregationBuilder(aggField);
        }
        return null;
    }
}

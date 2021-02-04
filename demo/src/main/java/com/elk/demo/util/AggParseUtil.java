package com.elk.demo.util;

import com.elk.demo.searchentity.result.AggResult;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/3 9:16
 * @Version V1.0
 */

public class AggParseUtil {

    public static void parseAgg(Aggregations aggregations, AggResult aggResult){
        List<Aggregation> aggregationsList = new ArrayList<>();
        aggResult.setAggregationList(aggregationsList);
        if(aggregations!=null){
            Map<String, Aggregation> stringAggregationMap = aggregations.asMap();
            if(stringAggregationMap!=null&&stringAggregationMap.size()>0){
                String implClassName = "";
                String groupName = "";
                for (Map.Entry<String, Aggregation> stringAggregationEntry : stringAggregationMap.entrySet()) {
                    Aggregation value = stringAggregationEntry.getValue();
                    groupName = stringAggregationEntry.getKey();
                    implClassName=value.getClass().getSimpleName();
                    aggregationsList.add(value);

                    /*if(AggType.AVG.getType().equals(type)){
                        ParsedAvg parsedAvg = (ParsedAvg)value;
                    }else if(AggType.MIN.getType().equals(type)){
                        ParsedMin parsed = (ParsedMin)value;
                        double value1 = parsed.getValue();
                        agg.put(key,value1);
                    }else if(AggType.MAX.getType().equals(type)){
                        ParsedMax parsed = (ParsedMax)value;
                        double value1 = parsed.getValue();
                        agg.put(key,value1);
                    }else if(AggType.SUM.getType().equals(type)){
                        ParsedSum parsed = (ParsedSum)value;
                        double value1 = parsed.getValue();
                        agg.put(key,value1);
                    }else if(AggType.VALUE_COUNT.getType().equals(type)){
                        ParsedValueCount parsed = (ParsedValueCount)value;
                        long value1 = parsed.getValue();
                        agg.put(key,value1);
                    }else if(AggType.CARDINALITY.getType().equals(type)){
                        ParsedCardinality parsed = (ParsedCardinality)value;
                    }else if(AggType.STATS.getType().equals(type)){
                        ParsedStats parsed = (ParsedStats)value;
                        agg.put(key,parsed);
                    }else if(AggType.EXTENDED_STATS.getType().equals(type)){
                        ParsedExtendedStats parsed = (ParsedExtendedStats)value;
                    }else if(AggType.PERCENTILES.getType().equals(type)){
                        ParsedPercentiles parsed = (ParsedPercentiles)value;
                    }else if(AggType.PERCENTILE_RANKS.getType().equals(type)){
                        ParsedRange parsed = (ParsedRange)value;
                    }else if(AggType.TOP_HITS.getType().equals(type)){
                        ParsedTopHits parsed = (ParsedTopHits)value;
                    }else if(AggType.GEO_BOUNDS.getType().equals(type)){
                        ParsedGeoBounds parsed = (ParsedGeoBounds)value;
                    }else if(AggType.GEO_CENTROID.getType().equals(type)){
                        ParsedGeoCentroid parsed = (ParsedGeoCentroid)value;

                    }*/
                }
                aggResult.setImplClassName(implClassName);
                aggResult.setGroupName(groupName);
            }
        }
    }
}

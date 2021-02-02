package com.elk.demo.elasticSearch.dao;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.elasticSearch.dao.search.ElasticSearchDao;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.agg.AvgAggField;
import com.elk.demo.searchentity.fieldparam.Field;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/2 10:13
 * @Version V1.0
 */
@Component
@Slf4j
public class AggregationsDao  {
    @Resource
    private RestHighLevelClient restHighLevelClient;
    public SearchResult avgAggs(SearchParam searchParam , AggField fields){
        AvgAggField avgAggField = (AvgAggField)fields;
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        AvgAggregationBuilder avgAggregationBuilder = new AvgAggregationBuilder(avgAggField.getGroupName());
        avgAggregationBuilder.field(avgAggField.getFieldName());
        SearchRequest searchRequest = new SearchRequest();
        searchSourceBuilder.aggregation(avgAggregationBuilder);
        searchRequest.source(searchSourceBuilder);
        searchRequest.indices(searchParam.getIndexName());
        System.out.println(searchSourceBuilder.toString());
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return SearchUtil.parseSearchResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

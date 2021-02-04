package com.elk.demo.elasticSearch.dao;

import com.elk.demo.factory.AggregationBuilderFactory;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

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
    //指标聚合
    public SearchResult metricsAggregations(SearchParam searchParam , AggField fields){
        SearchRequest searchRequest = genert(searchParam, fields);
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return SearchUtil.parseSearchResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private SearchRequest genert(SearchParam searchParam , AggField fields){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        AggregationBuilder queryBuilder = AggregationBuilderFactory.getQueryBuilder(fields);
        SearchRequest searchRequest = SearchUtil.genertSearchRequest(searchParam);
        SearchUtil.paddingSearchSourceBuilder(searchSourceBuilder,searchParam);
        searchSourceBuilder.aggregation(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        log.info("查询条件："+searchSourceBuilder.toString());
        log.info("查询参数："+searchRequest.toString());
        return searchRequest;
    }


}

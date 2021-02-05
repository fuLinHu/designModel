package com.elk.demo.elasticSearch.dao;

import com.elk.demo.factory.AggregationBuilderFactory;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/4 23:38
 * @Version V1.0
 */
@Repository
@Slf4j
public class SearchAggSearchComprehensiveDao {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public SearchResult searchAggSearchComprehensive(SearchParam searchParam, AggField aggField,Field... fields) {
        try {
            //1.生成SearchRequest
            SearchRequest searchRequest = SearchUtil.genertSearchRequest(searchParam);
            //2.构建QueryBuilder
            BoolQueryBuilder boolQueryBuilder = SearchUtil.genertBoolQueryBuilder(searchParam, fields);
            //3.构建AggregationBuilder
            AggregationBuilder aggQueryBuilder = AggregationBuilderFactory.getQueryBuilder(aggField);
            //4.构建SearchSourceBuilder
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            if(aggQueryBuilder!=null) searchSourceBuilder.aggregation(aggQueryBuilder);
            if(boolQueryBuilder!=null) searchSourceBuilder.query(boolQueryBuilder);
            SearchUtil.paddingSearchSourceBuilder(searchSourceBuilder,searchParam);
            log.info("搜索条件为：{}", searchSourceBuilder.toString());
            //5.将 searchSourceBuilder赋值给searchRequest
            searchRequest.source(searchSourceBuilder);
            //6.搜索
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //7.解析最后查询结果
            return SearchUtil.parseSearchResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}

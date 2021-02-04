package com.elk.demo.elasticSearch.dao;

import com.elk.demo.factory.AggregationBuilderFactory;
import com.elk.demo.factory.QueryBuilderFactory;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.fieldparam.Field;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.SearchUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/4 23:38
 * @Version V1.0
 */
public class SearchAggSearchComprehensiveDao {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public SearchResult searchAggSearchComprehensive(SearchParam searchParam, Field field, AggField aggField) {
        try {
            //1.生成SearchRequest
            SearchRequest searchRequest = SearchUtil.genertSearchRequest(searchParam);
            //2.构建QueryBuilder
            QueryBuilder queryBuilder = QueryBuilderFactory.getQueryBuilder(field);
            //3.构建AggregationBuilder
            AggregationBuilder aggQueryBuilder = AggregationBuilderFactory.getQueryBuilder(aggField);
            //4.构建SearchSourceBuilder
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.aggregation(aggQueryBuilder);
            searchSourceBuilder.query(queryBuilder);
            SearchUtil.paddingSearchSourceBuilder(searchSourceBuilder,searchParam);
            //5.搜索
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            //6.解析最后查询结果
            return SearchUtil.parseSearchResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}

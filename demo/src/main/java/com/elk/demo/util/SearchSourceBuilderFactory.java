package com.elk.demo.util;

import com.elk.demo.factory.QueryBuilderFactory;
import com.elk.demo.searchentity.fieldparam.Field;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 14:59
 * @Version V1.0
 */
public class SearchSourceBuilderFactory {

    public static SearchSourceBuilder getSearchSourceBuilder(Field field){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilderFactory.getQueryBuilder(field);
        SearchSourceBuilder query = searchSourceBuilder.query(queryBuilder);
        return query;
    }
}

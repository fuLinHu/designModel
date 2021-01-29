package com.elk.demo.elasticSearch.dao.search;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.factory.QueryBuilderFactory;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.fieldparam.Field;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 19:18
 * @Version V1.0
 */
@Repository
public class DismaxDao extends ElasticSearchDao{
    /**
     * @Author 付林虎
     * @Description //TODO  dis_max 搜索到的结果，应该是某一个field中匹配到了尽可能多的关键词，被排在前面；而不是尽可能多的field匹配到了少数的关键词，排在了前面
     * @Date 2021/1/28 9:52
     * @Param [searchParam, fields]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    @Override
    public List<JSONObject> search(SearchParam searchParam , Field...fields){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        DisMaxQueryBuilder disMaxQueryBuilder = QueryBuilders.disMaxQuery();
        if(searchParam.getTie_breaker()!=null) disMaxQueryBuilder.tieBreaker(searchParam.getTie_breaker());
        if(fields!=null&&fields.length>0){
            for (Field field : fields) {
                QueryBuilder queryBuilder = QueryBuilderFactory.getQueryBuilder(field);
                disMaxQueryBuilder.add(queryBuilder);
            }
        }
        SearchSourceBuilder query = searchSourceBuilder.query(disMaxQueryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(query, searchParam);
        return jsonObjects;
    }
}

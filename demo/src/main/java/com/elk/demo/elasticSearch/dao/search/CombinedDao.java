package com.elk.demo.elasticSearch.dao.search;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.fieldparam.Field;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.BoolQueryBuilderUtil;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 18:02
 * @Version V1.0
 */
@Repository
public class CombinedDao extends ElasticSearchDao{
    /**
     * @Author 付林虎
     * @Description //TODO 组合查询  组合  must must_not should filter等
     * @Date 2021/1/28 10:19
     * @Param [searchParam, fields]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    @Override
    public SearchResult search(SearchParam searchParam , Field...fields){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        Object minimum_should_match = searchParam.getMinimum_should_match();
        if(minimum_should_match!=null) {
            if((minimum_should_match+"").contains("%")){
                boolQueryBuilder.minimumShouldMatch(minimum_should_match+"");
            }else{
                boolQueryBuilder.minimumShouldMatch(Integer.parseInt(minimum_should_match+""));
            }
        }

        if(fields!=null&&fields.length>0){
            for (Field field : fields) {
                BoolQueryBuilderUtil.evaluationBool(boolQueryBuilder,field);
            }
        }

        SearchSourceBuilder query = searchSourceBuilder.query(boolQueryBuilder);
        return searchByQuery(query, searchParam);
    }
}

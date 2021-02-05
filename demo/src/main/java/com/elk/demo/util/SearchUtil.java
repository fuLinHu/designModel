package com.elk.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.searchentity.SearchPage;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.SortParam;
import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import com.elk.demo.searchentity.result.AggResult;
import com.elk.demo.searchentity.result.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;

import java.util.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/2 15:31
 * @Version V1.0
 */
@Slf4j
public class SearchUtil {

    public static SearchResult parseSearchResponse(SearchResponse searchResponse){
        SearchResult searchResult = new SearchResult();
        List<JSONObject> searchList = new ArrayList<>();
        searchResult.setSearchResult(searchList);
        AggResult aggResult = new AggResult();
        searchResult.setAggResult(aggResult);

        //解析聚合
        Aggregations aggregations = searchResponse.getAggregations();
        AggParseUtil.parseAgg(aggregations,aggResult);

        //解析 hits
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            JSONObject jsonObject = new JSONObject();
            boolean b = hit.hasSource();
            if(b){
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                Set<Map.Entry<String, Object>> entries = sourceAsMap.entrySet();
                String id = hit.getId();
                jsonObject.put("_id",id);
                for (Map.Entry<String, Object> entry : entries) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    jsonObject.put(key,value);
                }
            }
            HighlighteUtil.parseHitToHighLight(hit,jsonObject);
            searchList.add(jsonObject);
        }
        log.info("查询得最后结果为：{}",JSONObject.toJSONString(searchResult) );
        return searchResult;
    }

    public static SearchRequest genertSearchRequest(SearchParam searchParam){
        //searchRequest 是用于查询
        SearchRequest searchRequest = new SearchRequest();

        //查询 参数
        //查询哪些索引
        if (searchParam.getSearchType()!=null&&!"".equals(searchParam.getSearchType())) searchRequest.searchType(SearchType.fromString(searchParam.getSearchType()));
        String[] indexName = searchParam.getIndexName();
        if(indexName!=null&&indexName.length>0){
            searchRequest.indices(indexName);
        }
        //ignore_unavailable true/false   是否忽略不可用的索引
        // allow_no_indices true/false 默认为true. 当使用通配符查询时，当有索引不存在的时候是否返回查询失败。
        //allow_no_indices 跟 ignore_unavailable 都是用来防止没有索引的错误的，它们的区别是：
        //ignore_unavailable控制的是任何索引包括带通配符和不带通配符的，
        //allow_no_indices 控制的是带通配符的索引。
        //expand_wildcards
        //设置是否扩展通配符到closed的index中，open表示只在匹配并为open的index中查询，closed表示在匹配的所有的index中查询, 默认为closed
        //值为open，close，none，all。
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        //在es查询的时候我们可以控制Preference，来完成特定shard或节点上的数据查询，默认情况下查询是随机的。
        //假如现在我们有一份索引5个shard和3个副本，当一个查询请求过来的时候，查询操作如何执行，在没有使用路由的情况下5个shard的数据肯定都要查询，然后查询5个shard时候到底查的是主shard还是replia都是随机的。
        //通过偏好查询，我们可以有更多的选择定制，比如我们可以设置只查询主shard，或者只查replia，或者仅仅查某几个节点的数据。
        //下面来介绍下Preference相关的参数：
        /**
         （1）_primary：只查询主shard，也就是说不管你有多少个副本，只对主shard进行检索，这种场景可以用在所有副本不可用的时候，强制读取主shard数据。
         （2）_primary_first：优先读取主shard，如果主shard无效或者失败，则会读取其他shard
         （3）_replica：只查询replia
         （4）_replica_first：优先查询replia，如果replia无效就查询其他的shard。
         （5）_local：尽可能在本地执行查询，不跨网络
         （6）_prefer_nodes:abc,xyz 在指定的节点id上执行查询
         （7）_shards:2,3  查询指定分片上的数据，此外这种写法还可以和前面的用法组合，如：_shards:2,3|_primary ，查询分片2和3且在主节点上的数据
         （8）_only_nodes ：限制在特定的node上执行操作
         （9）Custom (string) value ：使用自定义的值来保证同一个值的数据，在一个shard里面，感觉有点像routing字段的功能，暂时没太理解如何使用这个功能，因为官网给的例子，只有查询，在索引的时候没有看到设置preference的功能：
         **/
        searchRequest.preference("_local");
        //根据指定的touting  找到对应的分片查询数据
        String[] routing = searchParam.getRouting();
        if(routing!=null&&routing.length>0){
            searchRequest.routing(routing);
        }
        return searchRequest;
    }

    public static void paddingSearchSourceBuilder(SearchSourceBuilder searchSourceBuilder, SearchParam searchParam){
        //是否有返回结果
        boolean fetchSource = searchParam.isFetchSource();
        searchSourceBuilder.fetchSource(searchParam.isFetchSource());
        //查询结果包含得字段 当 includeFields 为空得时候代表全包含
        if(fetchSource){
            String[] excludeFields = searchParam.getExcludeFields();
            String[] includeFields = searchParam.getIncludeFields();
            searchSourceBuilder.fetchSource(includeFields,excludeFields);
        }
        //是否排序
        SortParam[] sortParams = searchParam.getSortParam();
        if(sortParams!=null&&sortParams.length>0){
            for (SortParam sortParam : sortParams) {
                searchSourceBuilder.sort(new FieldSortBuilder(sortParam.getFieldName()).order(sortParam.getSortOrder()));
            }
        }
        //分页查询，如果为null 则不分页
        SearchPage searchPage = searchParam.getSearchPage();
        if(searchPage!=null){
            Integer from = searchPage.getFrom();
            Integer size = searchPage.getSize();
            if(from!=null&&size!=null){
                searchSourceBuilder.from(from).size(size);
            }else if(from==null&&size!=null){
                searchSourceBuilder.size(size);
            }
        }
    }
    /**
     * @Author 付林虎
     * @Description //TODO  生成 BoolQueryBuilder
     * @Date 2021/2/5 9:38
     * @Param [searchParam, fields]
     * @Version V1.0
     * @return org.elasticsearch.index.query.BoolQueryBuilder
     **/
    public static BoolQueryBuilder genertBoolQueryBuilder(SearchParam searchParam , Field...fields){
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
        return boolQueryBuilder;
    }

}

package com.elk.demo.elasticSearch.dao.search;

import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.HighlighteUtil;
import com.elk.demo.util.SearchSourceBuilderFactory;
import com.elk.demo.util.SearchUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 15:09
 * @Version V1.0
 */
@Slf4j
public class ElasticSearchDao {
    @Resource
    private RestHighLevelClient restHighLevelClient;


    public SearchResult search(SearchParam searchParam , Field... field){
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilderFactory.getSearchSourceBuilder(field[0]);
        SearchResult searchResult = searchByQuery(searchSourceBuilder, searchParam);
        return searchResult;
    }


    public SearchResult searchByQuery(SearchSourceBuilder searchSourceBuilder, SearchParam serachParam) {
        //1. 生成SearchRequest
        SearchRequest searchRequest = SearchUtil.genertSearchRequest(serachParam);
        //2. 填充一些公共参数到searchSourceBuilder
        SearchUtil.paddingSearchSourceBuilder(searchSourceBuilder,serachParam);
        //3. 填充一些关于高亮的参数到searchSourceBuilder
        HighlighteUtil.paddingHighLightParamToSearch(serachParam,searchSourceBuilder);
        //4. 将searchSourceBuilder赋值给SearchRequest
        searchRequest.source(searchSourceBuilder);
        log.info("查询条件："+searchSourceBuilder.toString());
        log.info("查询参数："+searchRequest.toString());
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            return SearchUtil.parseSearchResponse(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

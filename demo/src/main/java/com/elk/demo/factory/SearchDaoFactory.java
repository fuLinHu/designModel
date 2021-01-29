package com.elk.demo.factory;

import com.elk.demo.elasticSearch.dao.search.ElasticSearchDao;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.enumentity.SearchDataType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 19:32
 * @Version V1.0
 */
@Component
public class SearchDaoFactory  {
    @Resource
    private Map<String, ElasticSearchDao> elasticSearchDaoMap;

    public ElasticSearchDao getElasticSearchDao(SearchParam searchParam){
        SearchDataType searchDataType = searchParam.getSearchDataType();
        String type = searchDataType.getType();
        return elasticSearchDaoMap.get(type);
    }

}

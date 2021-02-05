package com.elk.demo.elasticSearch.impl;

import com.elk.demo.config.SearchDaoConfig;
import com.elk.demo.elasticSearch.ElasticSearchService;
import com.elk.demo.elasticSearch.dao.AggregationsDao;
import com.elk.demo.elasticSearch.dao.ElasticSearchCRUDDao;
import com.elk.demo.elasticSearch.dao.SearchAggSearchComprehensiveDao;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/20 17:28
 * @Version V1.0
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {
    @Autowired
    private ElasticSearchCRUDDao elasticSearchCRUDDao;
    @Resource
    private SearchDaoConfig searchDaoConfig;
    @Resource
    private AggregationsDao aggregationsDao;
    @Resource
    private SearchAggSearchComprehensiveDao searchAggSearchComprehensiveDao;


    @Override
    public BulkResponse saveBatchDoc(String indexName, List<?> list) {
        return elasticSearchCRUDDao.saveBatchDoc(indexName,list);
    }

    @Override
    public IndexResponse saveDoc(String indexName, Object object) {
        return elasticSearchCRUDDao.saveDoc(indexName,object);
    }

    @Override
    public boolean checkIndex(String index) {
        return elasticSearchCRUDDao.checkIndex(index);
    }

    @Override
    public CreateIndexResponse createIndex(String indexName) {
        return elasticSearchCRUDDao.createIndex(indexName);
    }

    @Override
    public AcknowledgedResponse createMapping(String indexName, XContentBuilder builder) {
        return elasticSearchCRUDDao.createMapping(indexName,builder);
    }
    @Override
    public UpdateResponse update(String indexName, String id, Object object){
        return elasticSearchCRUDDao.update(indexName, id,  object);
    }

    @Override
    public BulkResponse updateBatchDoc(String indexName, List<?> list, String esIdFieldName) {
        return elasticSearchCRUDDao.updateBatchDoc(indexName,list,esIdFieldName);
    }

    @Override
    public UpdateResponse updateAsync(String indexName, String id, Object object) {
        return elasticSearchCRUDDao.updateAsync(indexName,id,object);
    }

    @Override
    public BulkByScrollResponse deleteByQuery(String indexName, QueryBuilder queryBuilder){
        return elasticSearchCRUDDao.deleteByQuery(indexName,queryBuilder);
    }

    @Override
    public DeleteResponse deleteById(String indexName, String id) {
        return elasticSearchCRUDDao.deleteById(indexName,id);
    }


    @Override
    public SearchResult search(SearchParam searchParam, Field... Field) {
        return searchDaoConfig.getElasticSearchDao(searchParam).search(searchParam,Field);
    }

    @Override
    public SearchResult aggSearch(SearchParam searchParam, AggField aggField) {
        return aggregationsDao.metricsAggregations(searchParam,aggField);
    }
    @Override
    public SearchResult searchAggSearchComprehensive(SearchParam searchParam, AggField aggField, Field... fields){
        return searchAggSearchComprehensiveDao.searchAggSearchComprehensive(searchParam,aggField,fields);
    }


}

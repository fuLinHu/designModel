package com.elk.demo.elasticSearch.impl;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.elasticSearch.ElasticSearchService;
import com.elk.demo.elasticSearch.dao.ElasticSearchDao;
import com.elk.demo.searchentity.fieldparam.MatchField;
import com.elk.demo.searchentity.fieldparam.MatchPhraseField;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.fieldparam.TermField;
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
    private ElasticSearchDao elasticSearchDao;


    @Override
    public BulkResponse saveBatchDoc(String indexName, List<?> list) {
        return elasticSearchDao.saveBatchDoc(indexName,list);
    }

    @Override
    public IndexResponse saveDoc(String indexName, Object object) {
        return elasticSearchDao.saveDoc(indexName,object);
    }

    @Override
    public boolean checkIndex(String index) {
        return elasticSearchDao.checkIndex(index);
    }

    @Override
    public CreateIndexResponse createIndex(String indexName) {
        return elasticSearchDao.createIndex(indexName);
    }

    @Override
    public AcknowledgedResponse createMapping(String indexName, XContentBuilder builder) {
        return elasticSearchDao.createMapping(indexName,builder);
    }
    @Override
    public UpdateResponse update(String indexName, String id, Object object){
        return elasticSearchDao.update(indexName, id,  object);
    }

    @Override
    public BulkResponse updateBatchDoc(String indexName, List<?> list, String esIdFieldName) {
        return elasticSearchDao.updateBatchDoc(indexName,list,esIdFieldName);
    }

    @Override
    public UpdateResponse updateAsync(String indexName, String id, Object object) {
        return elasticSearchDao.updateAsync(indexName,id,object);
    }

    @Override
    public BulkByScrollResponse deleteByQuery(String indexName, QueryBuilder queryBuilder){
        return elasticSearchDao.deleteByQuery(indexName,queryBuilder);
    }

    @Override
    public DeleteResponse deleteById(String indexName, String id) {
        return elasticSearchDao.deleteById(indexName,id);
    }


    @Override
    public List<JSONObject> searchTerm(TermField termField, String... indexName) {
        return elasticSearchDao.searchTerm(termField,indexName);
    }

    @Override
    public List<JSONObject> searchTerms(TermField termField, String... indexName) {
        return elasticSearchDao.searchTerms(termField,indexName);
    }

    @Override
    public List<JSONObject> searchAll(String... indexName) {
        return elasticSearchDao.searchAll(indexName);
    }

    @Override
    public List<JSONObject> searchMatch(MatchField matchField, String... indexName) {
        return elasticSearchDao.searchMatch(matchField,indexName);
    }

    @Override
    public List<JSONObject> searchComprehensiveTest(SearchParam serachParam, String fieldName, Object objectValue, boolean ifOperatorAnd) {
        return elasticSearchDao.searchComprehensiveTest(serachParam,fieldName,objectValue,ifOperatorAnd);
    }

    @Override
    public List<JSONObject> searchShouldMatch(MatchField[] matchFields, String... indexName) {
        return elasticSearchDao.searchShouldMatch(matchFields,indexName);
    }

    @Override
    public List<JSONObject> searchMustMatch(MatchField[] MatchField, String... indexName) {
        return elasticSearchDao.searchMustMatch(MatchField,indexName);
    }

    @Override
    public List<JSONObject> searchMatchPhrase(MatchPhraseField matchPhraseField, String ... indexName) {
        return elasticSearchDao.searchMatchPhrase(matchPhraseField,indexName);
    }

}

package com.elk.demo.elasticSearch;

import com.alibaba.fastjson.JSONObject;
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

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/20 17:27
 * @Version V1.0
 */
public interface ElasticSearchService {
    BulkResponse saveBatchDoc(String indexName, List<?> list);

    IndexResponse saveDoc(String indexName, Object object);

    boolean checkIndex(String index);

    CreateIndexResponse createIndex(String indexName);

    AcknowledgedResponse createMapping(String indexName, XContentBuilder builder);

    UpdateResponse update(String indexName, String id, Object object);

    BulkResponse updateBatchDoc(String indexName,List<?> list,String esIdFieldName);

    UpdateResponse updateAsync(String indexName, String id, Object object);

    BulkByScrollResponse deleteByQuery(String indexName, QueryBuilder queryBuilder);

    DeleteResponse deleteById(String indexName, String id);


    List<JSONObject> searchTerm(TermField termField, String... indexName);

    List<JSONObject> searchTerms(TermField termField, String... indexName);

    List<JSONObject> searchAll(String... indexName);

    List<JSONObject> searchMatch(MatchField matchField,String ... indexName);

    List<JSONObject> searchComprehensiveTest(SearchParam serachParam, String fieldName, Object objectValue, boolean ifOperatorAnd);

    List<JSONObject> searchShouldMatch(MatchField[] matchFields, String... indexName);

    List<JSONObject> searchMustMatch(MatchField[] MatchField,String... indexName);

    List<JSONObject> searchMatchPhrase(MatchPhraseField matchPhraseField, String ... indexName);
}

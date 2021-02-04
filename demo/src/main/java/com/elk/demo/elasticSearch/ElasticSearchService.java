package com.elk.demo.elasticSearch;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.searchentity.agg.AggField;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.searchentity.fieldparam.Field;
import com.elk.demo.searchentity.SearchParam;
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


    SearchResult search(SearchParam searchParam, Field... Field);

    SearchResult aggSearch(SearchParam searchParam, AggField aggField);

    SearchResult searchAggSearchComprehensive(SearchParam searchParam, Field field, AggField aggField);
}

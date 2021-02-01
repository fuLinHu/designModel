package com.elk.demo.elasticSearch.dao;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/20 17:31
 * @Version V1.0
 */
@Component
@Slf4j
public class ElasticSearchCRUDDao {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @Author 付林虎
     * @Description //TODO  判断索引是否存在
     * @Date 2020/10/20 17:35
     * @Param [index]
     * @Version V1.0
     * @return boolean
     **/
    public  boolean checkIndex(String index) {
        GetIndexRequest request = new GetIndexRequest(index);
        try {
            boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
            return exists;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @Author 付林虎
     * @Description //TODO  创建索引
     * @Date 2020/10/20 17:49
     * @Param [indexName]
     * @Version V1.0
     * @return void
     **/
    public CreateIndexResponse createIndex(String indexName){
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);
        try {
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);
            return createIndexResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author 付林虎
     * @Description //TODO 创建mapping
     * @Date 2020/10/20 17:54
     * @Param [indexName, builder]
     * @Version V1.0
     * @return void
     *  XContentBuilder builder = XContentFactory.jsonBuilder()
     *                     .startObject()
     *                     .startObject("properties")
     *                     .startObject("id")
     *                     .field("type","keyword")
     *                     .field("index",true)
     *                     .endObject()
     *                     .startObject("pics")
     *                     .field("type","text")
     *                     .field("index",false)
     *                     .endObject()
     *                     .startObject("name")
     *                     .field("type","text")
     *                     .field("index",true)
     *                     //分词器采用ik_smart分词器
     *                     .field("analyzer","ik_smart")
     *                     .endObject()
     *                     .startObject("prices")
     *                     .field("type","double")
     *                     .field("index",true)
     *                     .endObject()
     *                     //可以按照城市排序，需要在其中再套一层，并且格式为keyword
     *                     .startObject("city")
     *                     .field("type","text")
     *                     .startObject("fields")
     *                     .startObject("raw")
     *                     .field("type","keyword")
     *                     .endObject()
     *                     .endObject()
     *                     .endObject()
     *                     //支持指定时间格式
     *                     .startObject("createTime")
     *                     .field("type","date")
     *                     .field("format","yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
     *                     .endObject()
     *                     .endObject()
     *                     .endObject();
     *
     *
     *
     **/
    public AcknowledgedResponse createMapping(String indexName,XContentBuilder builder){
        PutMappingRequest putMappingRequest = new PutMappingRequest(indexName);
        putMappingRequest.source(builder);
        try {
            AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().putMapping(putMappingRequest, RequestOptions.DEFAULT);
            return acknowledgedResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * @Author 付林虎
     * @Description //TODO  批量添加数据  注：如果添加得数据中包含 “_id” 那么数据库中有同样得id数据得时候是修改
     * @Date 2020/10/20 18:08
     * @Param [indexName, list]
     * @Version V1.0
     * @return void
     **/
    public BulkResponse saveBatchDoc(String indexName,List<?> list){
        BulkRequest bulkRequest = new BulkRequest();

        for (Object o : list) {
            bulkRequest.add(new IndexRequest(indexName).source(JSONObject.toJSONString(o), XContentType.JSON));
            bulkRequest.timeout("10m");
        }
        try {
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return bulkResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/22 15:39
     * @Param [indexName, object]
     * @Version V1.0
     * @return org.elasticsearch.action.index.IndexResponse
     **/
    public IndexResponse saveDoc(String indexName, Object object) {
        try {
            IndexRequest request = new IndexRequest();
            request.index(indexName).source(JSONObject.toJSONString(object), XContentType.JSON);
            log.info("添加一条数据："+request.toString());
            IndexResponse index = restHighLevelClient.index(request, RequestOptions.DEFAULT);
            return index;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @Author 付林虎
     * @Description //TODO 修改得本质是 先删除  在添加
     * @Date 2021/1/22 16:05
     * @Param [indexName, id, object]
     * @Version V1.0
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    public UpdateResponse update(String indexName, String id, Object object) {
        try {
            UpdateRequest updateRequest = new UpdateRequest(indexName, id).doc(JSONObject.toJSONString(object), XContentType.JSON);
            UpdateResponse update = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            return update;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author 付林虎
     * @Description //TODO  异步修改
     * @Date 2021/1/22 16:46
     * @Param [indexName, id, object]
     * @Version V1.0
     * @return org.elasticsearch.action.update.UpdateResponse
     **/
    public UpdateResponse updateAsync(String indexName, String id, Object object) {
        log.info("异步修改开始------");
        final UpdateResponse[] updateResponse = {null};
        try {
            UpdateRequest updateRequest = new UpdateRequest(indexName, id).doc(JSONObject.toJSONString(object), XContentType.JSON);
            restHighLevelClient.updateAsync(updateRequest, RequestOptions.DEFAULT,new ActionListener<UpdateResponse>(){
                @Override
                public void onResponse(UpdateResponse updateRes) {
                    updateResponse[0] = updateRes;
                    log.info("修改成功！"+updateRes.toString());
                }

                @Override
                public void onFailure(Exception e) {
                    log.info("修改失败！"+e.getMessage());
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updateResponse[0];
    }

    /**
     * @Author 付林虎
     * @Description //TODO  批量修改
     * @Date 2021/1/22 16:12
     * @Param [indexName, list, esIdFieldName]
     * @Version V1.0
     * @return org.elasticsearch.action.bulk.BulkResponse
     **/
    public BulkResponse updateBatchDoc(String indexName,List<?> list,String esIdFieldName) {
        try {
            BulkRequest bulkRequest = new BulkRequest();
            for (Object o : list) {
                JSONObject o1 = (JSONObject)JSONObject.toJSON(o);
                if(esIdFieldName==null||"".equals(esIdFieldName)){
                    esIdFieldName = "_id";
                }
                String es_id = o1.getString(esIdFieldName);
                bulkRequest.add(new IndexRequest(indexName).id(es_id).source(JSONObject.toJSONString(o), XContentType.JSON));
                bulkRequest.timeout("10m");
            }
            BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            return bulkResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @Author 付林虎
     * @Description //TODO 
     * @Date 2021/1/22 19:15
     * @Param [indexName, queryBuilder]
     * @Version V1.0
     * @return org.elasticsearch.index.reindex.BulkByScrollResponse
     **/
    public BulkByScrollResponse deleteByQuery(String indexName, QueryBuilder queryBuilder){
        try {
            DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(indexName);
            deleteByQueryRequest.setQuery(queryBuilder);
            BulkByScrollResponse bulkByScrollResponse = restHighLevelClient.deleteByQuery(deleteByQueryRequest, RequestOptions.DEFAULT);
            return bulkByScrollResponse;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Author 付林虎
     * @Description //TODO  根据主键删除
     * @Date 2021/1/25 10:33
     * @Param [indexName, id]
     * @Version V1.0
     * @return void
     **/
    public DeleteResponse deleteById(String indexName,String id){
        try {
            DeleteRequest deleteRequest = new DeleteRequest();
            DeleteRequest id1 = deleteRequest.index(indexName).id(id);
            DeleteResponse delete = restHighLevelClient.delete(id1, RequestOptions.DEFAULT);
            return delete;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //todo  有时间好好研究
    public void updateByQuery(String indexName, QueryBuilder queryBuilder){
        try {
            UpdateByQueryRequest updateByQueryRequest = new UpdateByQueryRequest(indexName);
            updateByQueryRequest.setQuery(queryBuilder);

            //updateByQueryRequest.
            restHighLevelClient.updateByQuery(updateByQueryRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/25 15:16
     * @Param [indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    /*public List<JSONObject> searchAll(SearchParam searchParam){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        List<JSONObject> jsonObjects = searchByQuery(searchSourceBuilder, searchParam);
        return jsonObjects;
    }*/


}

package com.elk.demo.elasticSearch.dao;


import com.alibaba.fastjson.JSONObject;
import com.elk.demo.searchentity.HighlightParam;
import com.elk.demo.searchentity.SearchPage;
import com.elk.demo.searchentity.SearchParam;
import com.elk.demo.searchentity.MatchField;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/20 17:31
 * @Version V1.0
 */
@Component
@Slf4j
public class ElasticSearchDao {
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


    //======================================search======================================
    public List<JSONObject> searchByQuery(SearchSourceBuilder searchSourceBuilder,String... indexName){
        List<JSONObject> list = new ArrayList<>();
        //searchRequest 是用于查询
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(indexName);
        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        searchRequest.preference("_local");
        searchRequest.source(searchSourceBuilder);
        log.info("查询条件："+searchSourceBuilder.toString());
        log.info("查询参数："+searchRequest.toString());
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();

            for (SearchHit hit : hits) {
                JSONObject jsonObject = new JSONObject();
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                boolean b = hit.hasSource();
                if(b){
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    Set<Map.Entry<String, Object>> entries = sourceAsMap.entrySet();
                    String id = hit.getId();
                    jsonObject.put("_id",id);
                    for (Map.Entry<String, Object> entry : entries) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if(highlightFields.containsKey(key)){
                            HighlightField highlightField = highlightFields.get(key);
                            Text[] fragments = highlightField.getFragments();
                            String highLightContent = "";
                            for (Text fragment : fragments) {
                                highLightContent+=fragment;
                            }
                            jsonObject.put(key,highLightContent);
                        }else{
                            jsonObject.put(key,value);
                        }
                    }
                    list.add(jsonObject);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * @Author 付林虎
     * @Description //TODO   根据某个字段 精确查询（ fieldName+".keyword"  是改字段对应得全数据）  对应得字段值  是分词存储得
     * @Description //todo  如果  不带 .keyword  则搜索得 object 是 精确对应   分词后的数据
     * @Description //todo  比如  中华人民共和国  ————》》 分词以后  中华 人民  共和国
     * @Description //todo  term(fieldName+".keyword","中华人民共和国")  结果不为空，其余结果为空
     * @Description //todo  term(fieldName,"中华")  term(fieldName,"人民") term(fieldName,"共和国") 结果不为空，其余结果为空
     * @Date 2021/1/25 10:52
     * @Param [fieldName, object, indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchTermFieldTypeText(String fieldName,Object object,boolean ifkeyword,String... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(ifkeyword){
            fieldName =  fieldName+".keyword";
        }
        QueryBuilder queryBuilder = new TermQueryBuilder(fieldName,object);
        SearchSourceBuilder query = searchSourceBuilder.query(queryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(query, indexName);
        return jsonObjects;
    }



    /**
     * @Author 付林虎
     * @Description //TODO  根据某个字段 精确查询  TypeKeyword 对应得字段值 是不分词存储
     * @Date 2021/1/25 13:27
     * @Param [fieldName, object, indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchTermFieldTypeKeyword(String fieldName,Object object,String... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = new TermQueryBuilder(fieldName,object);
        SearchSourceBuilder query = searchSourceBuilder.query(queryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(query, indexName);
        return jsonObjects;
    }



    /**
     * @Author 付林虎
     * @Description //TODO
     * @Date 2021/1/25 15:16
     * @Param [indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchAll(String... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        List<JSONObject> jsonObjects = searchByQuery(searchSourceBuilder, indexName);
        return jsonObjects;
    }


    /**
     * @Author 付林虎
     * @Description //TODO  match 查询   match 是分词查询  mathch  需要和 跟keyword的完全匹配可以有结果
     * @Description //TODO  match分词，text也分词，只要match的分词结果和text的分词结果有相同的就匹配。但当ifOperatorAnd 为 true
     * @Description //TODO  那么  match 分词结果 要完全 在 text 中匹配，可以 不考虑顺序
     * @Date 2021/1/25 19:37
     * @Param [fieldName, objectValue, ifOperatorAnd, indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchMatch(String fieldName,Object objectValue,boolean ifOperatorAnd,String ... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName,objectValue);
        if(ifOperatorAnd){
            matchQueryBuilder.operator(Operator.AND);
        }
        //后期仔细研究参数含义
        //matchQueryBuilder.fuzziness(0);
        searchSourceBuilder.query(matchQueryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(searchSourceBuilder, indexName);
        return jsonObjects;
    }

    public List<JSONObject> searchComprehensiveTest(SearchParam serachParam,String fieldName,Object objectValue,boolean ifOperatorAnd){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(fieldName,objectValue);
        if(ifOperatorAnd){
            matchQueryBuilder.operator(Operator.AND);
        }
        searchSourceBuilder.query(matchQueryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(searchSourceBuilder, serachParam);
        return jsonObjects;
    }

    private List<JSONObject> searchByQuery(SearchSourceBuilder searchSourceBuilder, SearchParam serachParam) {
        List<JSONObject> list = new ArrayList<>();
        //searchRequest 是用于查询
        SearchRequest searchRequest = new SearchRequest();

        //查询哪些索引
        String[] indexName = serachParam.getIndexName();
        if(indexName!=null&&indexName.length>0){
            searchRequest.indices(indexName);
        }
        //是否有返回结果
        boolean fetchSource = serachParam.isFetchSource();
        searchSourceBuilder.fetchSource(fetchSource);

        //查询结果包含得字段 当 includeFields 为空得时候代表全包含
        if(fetchSource){
            String[] excludeFields = serachParam.getExcludeFields();
            String[] includeFields = serachParam.getIncludeFields();
            searchSourceBuilder.fetchSource(includeFields,excludeFields);
        }

        //分页查询，如果为null 则不分页
        SearchPage searchPage = serachParam.getSearchPage();
        if(searchPage!=null){
            Integer from = searchPage.getFrom();
            Integer size = searchPage.getSize();
            searchSourceBuilder.from(from).size(size);
        }

        HighlightParam highlightParam = serachParam.getHighlightParam();
        if(highlightParam!=null){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            HighlightParam.HiglightField[] higlightFields = highlightParam.getHiglightFields();
            if(higlightFields!=null&&higlightFields.length>0){
                for (HighlightParam.HiglightField higlightFieldParam : higlightFields) {

                    HighlightBuilder.Field highlightField =new HighlightBuilder.Field(higlightFieldParam.getHighField());

                    if (higlightFieldParam.getHighlighterType()!=null&&!"".equals(higlightFieldParam.getHighlighterType())) highlightField.highlighterType(higlightFieldParam.getHighlighterType());
                    if (higlightFieldParam.getFragment_offset()!=null) highlightField.fragmentOffset(higlightFieldParam.getFragment_offset());
                    if (higlightFieldParam.getFragment_size()!=null) highlightField.fragmentSize(higlightFieldParam.getFragment_size());
                    if (higlightFieldParam.getNo_match_size()!=null) highlightField.noMatchSize(higlightFieldParam.getNo_match_size());
                    if (higlightFieldParam.getNumber_of_fragments()!=null) highlightField.numOfFragments(higlightFieldParam.getNumber_of_fragments());

                    highlightBuilder.field(highlightField);
                }
            }
            String[] pre_tags = highlightParam.getPre_tags();
            if(pre_tags!=null&&pre_tags.length>0){
                highlightBuilder.preTags(pre_tags);
            }
            String[] post_tags = highlightParam.getPost_tags();
            if(post_tags!=null&&pre_tags.length>0){
                highlightBuilder.postTags(post_tags);
            }
            searchSourceBuilder.highlighter(highlightBuilder);
        }

        searchRequest.indicesOptions(IndicesOptions.lenientExpandOpen());
        searchRequest.preference("_local");
        searchRequest.source(searchSourceBuilder);
        log.info("查询条件："+searchSourceBuilder.toString());
        log.info("查询参数："+searchRequest.toString());
        try {
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = search.getHits();

            for (SearchHit hit : hits) {
                JSONObject jsonObject = new JSONObject();
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                boolean b = hit.hasSource();
                if(b){
                    Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                    Set<Map.Entry<String, Object>> entries = sourceAsMap.entrySet();
                    String id = hit.getId();
                    jsonObject.put("_id",id);
                    for (Map.Entry<String, Object> entry : entries) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        if(highlightFields.containsKey(key)){
                            HighlightField highlightField = highlightFields.get(key);
                            String highLightContent = getHighLightContent(highlightField);
                            jsonObject.put(key,highLightContent);
                        }else{
                            jsonObject.put(key,value);
                        }
                    }
                }else {
                    if(highlightFields!=null&&highlightFields.size()>0){
                        for (Map.Entry<String, HighlightField> stringHighlightFieldEntry : highlightFields.entrySet()) {
                            String key = stringHighlightFieldEntry.getKey();
                            HighlightField highlightField = stringHighlightFieldEntry.getValue();
                            String highLightContent = getHighLightContent(highlightField);
                            jsonObject.put(key,highLightContent);
                        }
                    }
                }
                list.add(jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String getHighLightContent(HighlightField highlightField){
        Text[] fragments = highlightField.getFragments();
        String highLightContent = "";
        for (Text fragment : fragments) {
            highLightContent+=fragment;
        }
        return highLightContent;
    }

    /**
     * @Author 付林虎
     * @Description //TODO  match should   (or)
     * @Date 2021/1/26 16:04
     * @Param [MatchField, indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchShouldMatch(MatchField[] matchFields,String... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(matchFields!=null&&matchFields.length>0){
            putShouldMatchBool(boolQueryBuilder,matchFields);
        }
        SearchSourceBuilder query = searchSourceBuilder.query(boolQueryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(query, SearchParam.builder().indexName(indexName).build());
        return jsonObjects;
    }

    /**
     * @Author 付林虎
     * @Description //TODO  match must   (and)
     * @Date 2021/1/26 16:31
     * @Param [MatchField, indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchMustMatch(MatchField[] matchFields,String... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(matchFields!=null&&matchFields.length>0){
            putMustMatchBool(boolQueryBuilder,matchFields);
        }
        SearchSourceBuilder query = searchSourceBuilder.query(boolQueryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(query, SearchParam.builder().indexName(indexName).build());
        return jsonObjects;
    }

    /**
     * @Author 付林虎
     * @Description //TODO  must  should
     * @Date 2021/1/26 17:12
     * @Param [mushMatchFields, shouldMatchFields, indexName]
     * @Version V1.0
     * @return java.util.List<com.alibaba.fastjson.JSONObject>
     **/
    public List<JSONObject> searchMustShouldMatch(MatchField[] mushMatchFields,MatchField[] shouldMatchFields,String... indexName){
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if(mushMatchFields!=null&&mushMatchFields.length>0){
            putMustMatchBool(boolQueryBuilder,mushMatchFields);
        }
        if(shouldMatchFields!=null&&shouldMatchFields.length>0){
            putShouldMatchBool(boolQueryBuilder,shouldMatchFields);
        }
        SearchSourceBuilder query = searchSourceBuilder.query(boolQueryBuilder);
        List<JSONObject> jsonObjects = searchByQuery(query, SearchParam.builder().indexName(indexName).build());
        return jsonObjects;
    }

    private void putShouldMatchBool(BoolQueryBuilder boolQueryBuilder,MatchField[] matchFields){
        for (MatchField matchField : matchFields) {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(matchField.getFieldName(),matchField.getFieldValue());
            if (matchField.getOperator()!=null&&!"".equals(matchField.getOperator())) matchQueryBuilder.operator(Operator.valueOf(matchField.getOperator()));
            boolQueryBuilder.should(matchQueryBuilder);
        }
    }
    private void putMustMatchBool(BoolQueryBuilder boolQueryBuilder,MatchField[] matchFields){
        for (MatchField matchField : matchFields) {
            MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(matchField.getFieldName(),matchField.getFieldValue());
            if (matchField.getOperator()!=null&&!"".equals(matchField.getOperator())) matchQueryBuilder.operator(Operator.valueOf(matchField.getOperator()));
            boolQueryBuilder.must(matchQueryBuilder);
        }
    }

    //==================================================================================//





}

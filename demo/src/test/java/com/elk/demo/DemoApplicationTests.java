package com.elk.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.elk.demo.elasticSearch.ElasticSearchService;
import com.elk.demo.elasticSearch.dao.AggregationsDao;
import com.elk.demo.searchentity.*;
import com.elk.demo.searchentity.agg.AvgAggField;
import com.elk.demo.searchentity.enumentity.BoolQueryType;
import com.elk.demo.searchentity.enumentity.FieldType;
import com.elk.demo.searchentity.enumentity.SearchDataType;
import com.elk.demo.searchentity.fieldparam.*;
import com.elk.demo.searchentity.result.SearchResult;
import com.elk.demo.util.BoolQueryBuilderUtil;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {
    @Resource
    private ElasticSearchService elasticSearchService;

    private String indexname = "foresttiger1";
    @Resource
    private AggregationsDao  aggregationsDao;



    public void deleteById(){
        elasticSearchService.deleteById(indexname,"8IICKXcB7b6mJsY-ONQZ");
    }

    public void updateBatch(){
        List<JSONObject> jsonObjects = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","tiger2_update");
        jsonObject.put("age",1000);
        jsonObject.put("birthday",new Date());
        jsonObject.put("heigh",175.5);
        jsonObject.put("esId","74LvKHcB7b6mJsY-7NTx");

        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name","tiger2_update");
        jsonObject1.put("age",1000);
        jsonObject1.put("birthday",new Date());
        jsonObject1.put("heigh",195.5);
        jsonObject1.put("esId","8YIpKXcB7b6mJsY-IdQI");
        jsonObjects.add(jsonObject);
        jsonObjects.add(jsonObject1);

        elasticSearchService.updateBatchDoc(indexname,jsonObjects,"esId");
    }

    public void deleteQuery(){
        TermQueryBuilder age = new TermQueryBuilder("age", 1000);
        elasticSearchService.deleteByQuery(indexname,age);
    }


    public void update(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","tiger2");
        jsonObject.put("age",1000);
        jsonObject.put("birthday",new Date());
        jsonObject.put("heigh",175.5);
        elasticSearchService.update(indexname,"74LvKHcB7b6mJsY-7NTx",jsonObject);
    }

    public void updateAsync(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","tiger2");
        jsonObject.put("age",1000);
        jsonObject.put("birthday",new Date());
        jsonObject.put("heigh",177.5);
        elasticSearchService.updateAsync(indexname,"74LvKHcB7b6mJsY-7NTx",jsonObject);
    }



    private void save(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","tiger");
        jsonObject.put("title","中国建国100周年");
        jsonObject.put("age",23);
        jsonObject.put("birthday",new Date());
        jsonObject.put("heigh",176);
        jsonObject.put("message","习主席做出重要讲话");
        JSONObject com = new JSONObject();
        com.put("age",24);
        com.put("name","王老五");
        jsonObject.put("comment",com);
        elasticSearchService.saveDoc(indexname,jsonObject);
    }

    private void saveBatch(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name","tiger");
        jsonObject.put("age",10);
        jsonObject.put("birthday",new Date());
        jsonObject.put("heigh",183.5);
        List<JSONObject> list = new ArrayList<>();
        list.add(jsonObject);
        elasticSearchService.saveBatchDoc(indexname,list);
    }

    private void createMapping(){
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder();
            XContentBuilder properties = builder.startObject().startObject("properties");
            XContentBuilder xContentBuilder = properties.startObject("name").field("type", "keyword").field("index", true).endObject();
            xContentBuilder = xContentBuilder.startObject("age").field("type","integer").field("index", true).endObject();
            xContentBuilder = xContentBuilder.startObject("birthday").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis").endObject();
            xContentBuilder = xContentBuilder.startObject("heigh").field("type", "double").field("index", true).endObject();
            xContentBuilder = xContentBuilder.startObject("comment").field("type","nested")
                                .startObject("properties")
                                    .startObject("name")
                                        .field("type","text")
                                        .field("analyzer","ik_max_word")
                                    .endObject()
                                    .startObject("age")
                                        .field("type","integer")
                                    .endObject()
                                .endObject()
                             .endObject();

            xContentBuilder.endObject().endObject();
            boolean foresttiger = elasticSearchService.checkIndex(indexname);
            if(!foresttiger){
                elasticSearchService.createIndex(indexname);
                elasticSearchService.createMapping(indexname,xContentBuilder);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchTermFieldTypeText(){
        TermField field = TermField.builder()
                .boost(10f)
                .fieldTypeIfKeyWord(false)
                .build();
        SearchParam searchParam = SearchParam.builder()
                .searchDataType(SearchDataType.Term)
                .indexName(new String[]{"foresttiger"})
                .build();


        SearchResult search = elasticSearchService.search(searchParam,field);
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }

    private void searchTermFieldTypeKeyword(){
        TermField field = TermField.builder()
                .boost(10f)
                .fieldTypeIfKeyWord(true)
                .fieldName("name")
                .fieldValue(new String[]{"can test"})
                .build();
        SearchParam searchParam = SearchParam.builder()
                .searchDataType(SearchDataType.Term)
                .indexName(new String[]{"foresttiger"})
                .build();

        SearchResult search = elasticSearchService.search(searchParam,field);
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }


    private void searchMatch(){
        MatchField field = MatchField.builder()
                .fieldName("message")
                .fieldValue("can test")
                .lenient(true)
                .minimum_should_match("75%")
                .build();
        SearchParam searchParam = SearchParam.builder()
                .searchDataType(SearchDataType.Match)
                .indexName(new String[]{"foresttiger"})
                .build();

        SearchResult search = elasticSearchService.search(searchParam,field);
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }



    private void searchComprehensiveTest(){


        HighlightParam.HiglightField higlightField = HighlightParam.HiglightField.builder()
                .highField("message")
                .highlighterType("plain")
                .build();

        HighlightParam highlightParam = HighlightParam.builder()
                .higlightFields(new HighlightParam.HiglightField[]{higlightField})
                .pre_tags(new String[]{"<h1>"})
                .post_tags(new String[]{"</h1>"})
                .build();


        SearchParam searchParam = SearchParam.builder()
                .indexName(new String[]{"foresttiger"})
                .searchPage(new SearchPage(0, 2))
                .searchDataType(SearchDataType.Combined)
                .fetchSource(true)
                .highlightParam(highlightParam)
                .build();


        MatchField build1 = MatchField.builder()
                .fieldName("message")
                .fieldValue("test")
                .boolQueryType(BoolQueryType.FILTER)
                .build();

        SearchResult search = elasticSearchService.search(searchParam,build1);
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }

    private void searchShouldMatch(){
        MatchField build1 = MatchField.builder()
                .fieldName("message")
                .fieldValue("can")
                .operator(Operator.OR)
                .build();


        MatchField build = MatchField.builder()
                .fieldName("title")
                .fieldValue("百")
                .build();
        SearchParam build2 = SearchParam.builder()
                .indexName(new String[]{indexname})
                .searchDataType(SearchDataType.Combined)
                .build();

        SearchResult search = elasticSearchService.search(build2,new MatchField[]{build1, build});
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }

    private void searchMustMatch(){
        MatchField build1 = MatchField.builder()
                .fieldName("message")
                .fieldValue("can")
                .operator(Operator.AND)
                .boolQueryType(BoolQueryType.MUST)
                .build();


        MatchField build = MatchField.builder()
                .fieldName("title")
                .fieldValue("百")
                .boolQueryType(BoolQueryType.MUST)
                .build();
        SearchParam build2 = SearchParam.builder()
                .indexName(new String[]{indexname})
                .searchDataType(SearchDataType.Combined)
                .build();

        SearchResult search = elasticSearchService.search(build2,new MatchField[]{build1, build});
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }



    private void searchFilter(){

        RangeField age = RangeField.builder()
                .boolQueryType(BoolQueryType.FILTER)
                .fromTo( RangeField.FromToIfIncludeLowerUpper.builder()
                        .from(10)
                        .to(1006)
                        .include_lower(true)
                        .include_upper(true)
                        .build()
                )
                .fieldName("age")
                .build();
        MatchField title = MatchField.builder()
                .fieldName("title")
                .fieldValue("1")
                .boolQueryType(BoolQueryType.SHOULD)
                .build();



        SearchParam searchParam = SearchParam.builder()
                .searchDataType(SearchDataType.Combined)
                .indexName(new String[]{"foresttiger"})
                .build();

        SearchResult search = elasticSearchService.search(searchParam, new Field[]{age, title});

        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }

    private void serchMatchPhrase(){

        MatchPhraseField field = MatchPhraseField.builder()
                .fieldName("message")
                .fieldValue("test can")
                .slop(1)
                .build();
        SearchParam searchParam = SearchParam.builder()
                .searchDataType(SearchDataType.MatchPhrase)
                .indexName(new String[]{"foresttiger"})
                .build();

        SearchResult search = elasticSearchService.search(searchParam,field);
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }

    private void Nested(){
        SearchParam searchParam = SearchParam.builder()
                .indexName(new String[]{indexname})
                .build();

        MatchField heigh = MatchField.builder()
                .fieldName("heigh")
                .fieldValue(166)
                .boolQueryType(BoolQueryType.SHOULD)
                .build();


        MatchField name = MatchField.builder()
                .fieldName("comment.name")
                .fieldValue("付林虎")
                .boolQueryType(BoolQueryType.MUST)
                .build();
        MatchField age = MatchField.builder()
                .fieldName("comment.age")
                .boolQueryType(BoolQueryType.MUST)
                .fieldValue(24)
                .build();
        BoolQueryBuilder boolQueryBuilder = BoolQueryBuilderUtil.evaluationBool(new Field[]{name, age});


        NestedField comment = NestedField.builder()
                .boolQueryBuilder(boolQueryBuilder)
                .path("comment")
                .boolQueryType(BoolQueryType.SHOULD)
                .build();


        SearchResult search = elasticSearchService.search(searchParam, new Field[]{heigh, comment});
        if(search!=null){
            Object o = JSONArray.toJSON(search.getSearchResult());
            System.out.println(o);
        }
    }
    @Test
    void contextLoads() throws Exception {
        avgAgg();
    }

    private void avgAgg(){
        SearchParam searchParam = SearchParam.builder()
                .indexName(new String[]{indexname})
                .build();
        AvgAggField build = AvgAggField.builder()
                .fieldName("heigh")
                .groupName("heigh_groupy")
                .build();
        SearchResult searchResult = aggregationsDao.avgAggs(searchParam, build);
        parse(searchResult);
    }

    private void parse(SearchResult searchResult){
        if(searchResult!=null){
            Object o = JSONArray.toJSON(searchResult.getSearchResult());
            System.out.println(o);
            System.out.println(searchResult.getAggResult());
        }
    }



}

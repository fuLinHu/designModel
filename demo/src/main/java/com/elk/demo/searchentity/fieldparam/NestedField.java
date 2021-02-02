package com.elk.demo.searchentity.fieldparam;

import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/1 19:28
 * @Version V1.0
 */
@SuperBuilder
@Data
public class NestedField extends Field {
    //  当然，一个nested查询可以匹配多个nested文本。每个匹配的nested文本都有它自己相关评分，但是这些评分必须归为一个总分应用于根文本上。
    // 默认会平均所有匹配的nested文本的分数。当然，也可以通过设定score_modec参数为avg,max,sum,或者甚至为none(根文本获得一致评分1.0)。
    private ScoreMode scoreMode;
    /**
     要嵌套的那个key
     比如一篇文章
     {
       title:"新中国成立100周年"，
       comments:{
           "concent":"写的太好了"，
           "name":"小红",
            "age":10
        }
     }
     那么要查询  评论人为 小红  那么  path 为  comments
     {
     "query": {
     "bool": {
     "must": [
     {
     "match": {
     "title": "eggs"
     }
     },
     {
     "nested": {
     "path": "comments",
     "score_mode": "max",
     "query": {
     "bool": {
     "must": [
     {
     "match": {
     "comments.name": "john"
     }
     },
     {
     "match": {
     "comments.age": 28
     }
     }
     ]
     }
     }
     }
     }
     ]
     }
     }
     }
     **/

    private String path;

    /**
     NestedQueryBuilder  的 构造函数的 第二个参数 ，必须赋值  可以
     用{@link com.elk.demo.util.BoolQueryBuilderUtil#evaluationBool(com.elk.demo.searchentity.fieldparam.Field[])}
     进行构造
     **/
    private BoolQueryBuilder boolQueryBuilder;


}

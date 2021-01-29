package com.elk.demo.elasticSearch.dao.search;

import org.springframework.stereotype.Repository;

/**
 * @className
 * @Description //TODO  match 查询   match 是分词查询  mathch  需要和 跟keyword的完全匹配可以有结果
 * @Description //TODO  match分词，text也分词，只要match的分词结果和text的分词结果有相同的就匹配。但当ifOperatorAnd 为 true
 * @Description //TODO  那么  match 分词结果 要完全 在 text 中匹配，可以 不考虑顺序
 * @Author 付林虎
 * @Date 2021/1/28 16:05
 * @Version V1.0
 */
@Repository
public class MatchDao extends ElasticSearchDao {

}

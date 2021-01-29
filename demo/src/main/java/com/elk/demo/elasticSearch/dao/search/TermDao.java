package com.elk.demo.elasticSearch.dao.search;

import org.springframework.stereotype.Repository;

/**
 * @className
 * @Description //TODO   当字段类型为  text的时候是需要分词的
 * @Description //TODO   根据某个字段 精确查询（ fieldName+".keyword"  是改字段对应得全数据）  对应得字段值  是分词存储得
 * @Description //todo  如果  不带 .keyword  则搜索得 object 是 精确对应   分词后的数据
 * @Description //todo  比如  中华人民共和国  ————》》 分词以后  中华 人民  共和国
 * @Description //todo  term(fieldName+".keyword","中华人民共和国")  结果不为空，其余结果为空
 * @Description //todo  term(fieldName,"中华")  term(fieldName,"人民") term(fieldName,"共和国") 结果不为空，其余结果为空
 * @Description //todo  当字段类型是 keyword  则改字段不进行分词存储，则查询的时候 不需要添加 ".keyword"，精确查询整个字段
 * @Author 付林虎
 * @Date 2021/1/28 15:23
 * @Version V1.0
 */
@Repository
public class TermDao extends ElasticSearchDao {
}

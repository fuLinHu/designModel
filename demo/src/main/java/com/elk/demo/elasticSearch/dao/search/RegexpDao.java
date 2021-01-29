package com.elk.demo.elasticSearch.dao.search;

import org.springframework.stereotype.Repository;

/**
 * @className
 * @Description //TODO  基于词条，正则表达式查询的性能在很大程度上取决于所选的正则表达式。匹配像 .* 这样的所有内容非常慢，并且使用环绕正则表达式。
 * @Description //TODO  如果可能，您应该在正则表达式开始之前尝试使用长前缀。像 .* ？+这样的通配符匹配器会降低性能。
 * @Author 付林虎
 * @Date 2021/1/28 15:18
 * @Version V1.0
 */
@Repository
public class RegexpDao extends ElasticSearchDao {
}

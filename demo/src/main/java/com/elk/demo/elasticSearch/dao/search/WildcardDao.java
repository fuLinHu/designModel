package com.elk.demo.elasticSearch.dao.search;

import org.springframework.stereotype.Repository;

/**
 * @className
 * @Description //TODO 通配符 模糊查询 类似 mysql like 允许使用通配符*和?来进行查询 *代表0个或多个字符 ?代表任意1个字符
 * @Description //TODO 请注意，此查询可能很慢，因为它需要迭代多个词。为了防止极慢的通配符查询，通配符术语不应该以通配符*或？之一开头。通配符查询映射到Lucene WildcardQuery
 * @Description //TODO 如果满足你的需求，前缀匹配是优于wildcard和regexp。
 * @Author 付林虎
 * @Date 2021/1/28 15:19
 * @Version V1.0
 */
@Repository
public class WildcardDao extends ElasticSearchDao {
}

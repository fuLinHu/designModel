package com.elk.demo.searchentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/25 17:14
 * @Version V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParam {
    //是否有返回结果
    @Builder.Default
    private boolean fetchSource = true;
    //查询结果包含得字段 当 includeFields 为空得时候代表全包含
    private String[] includeFields;
    //查询结果不包含得字段 当 excludeFields 为空得时候代表全包含
    private String[] excludeFields ;
    //需要搜索得索引  如果为null  代表搜索所有
    private String[] indexName;
    //分页查询，如果为null 则不分页
    private SearchPage searchPage;
    // highlightParam 为null 则没有任何字段需要高亮
    private HighlightParam highlightParam;

}

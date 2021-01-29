package com.elk.demo.searchentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/29 11:22
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortParam {
    //排序的字段名称
    private String fieldName;
    //正序 倒叙
    private SortOrder sortOrder;
}

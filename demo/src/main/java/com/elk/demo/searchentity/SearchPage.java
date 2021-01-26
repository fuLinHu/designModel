package com.elk.demo.searchentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/25 17:15
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPage {
    //起始位置是0
    private Integer from;
    private Integer size;
}

package com.elk.demo.searchentity.enumentity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/29 9:09
 * @Version V1.0
 */
@Getter
@AllArgsConstructor
public enum  HighlighterType {

    //highlight 类型{@code unified}, {@code plain } and {@code fvj}.
    UNIFIED(1,"unified"),
    PLAIN(1,"plain"),
    FVJ(1,"fvj");
    private Integer code;
    private String type;

}

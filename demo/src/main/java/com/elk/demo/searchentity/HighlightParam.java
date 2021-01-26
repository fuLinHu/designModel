package com.elk.demo.searchentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.net.www.content.text.plain;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/26 10:44
 * @Version V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HighlightParam {
    //高亮字段设置
    private HiglightField[] higlightFields;
    //高亮得前缀标签
    private String[] pre_tags;
    //高亮得后置缀标签
    private String[] post_tags;

    @Data
    @Builder
    public static class HiglightField {
        //高亮字段
        private String highField;

        //这些参数可以设置在全局中

        //highlight 类型{@code unified}, {@code plain } and {@code fvj}.
        private String highlighterType; //默认是 unified

        private Integer fragment_size; //突出显示的片段的大小（以字符为单位）默认为100

        private Integer fragment_offset; //控制要开始突出显示的边距。仅在使用 fvh 有效

        private Integer no_match_size; //如果没有要突出显示的匹配片段，则要从字段开头返回的文本量。默认为0（不返回任何内容）

        private Integer number_of_fragments;// 要返回的最大片段数。如果片段数设置为0，则不返回任何片段。而是突出显示并返回整个字段内容。当您需要突出显示标题或地址等短文本时，这可能很方便，但不需要分段。如果number_of_fragments 为0，fragment_size则忽略。默认为5





    }
}

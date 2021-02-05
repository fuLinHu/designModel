package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.fieldparam.searchbasefield.SearchField;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/5 12:06
 * @Version V1.0
 */
@Data
@SuperBuilder
public class MatchPhrasePrefixField extends SearchField {
    private String analyzer;

    /**
     max_expansions
     官方文档中说 match_phrase_prefix 查询中有个参数 max_expansions 说的是参数 max_expansions 控制着可以与前缀匹配的词的数量，默认值是 50。

     以 I like swi 查询为例，它会先查找第一个与前缀 swi 匹配的词，然后依次查找搜集与之匹配的词（按字母顺序），直到没有更多可匹配的词或当数量超过 max_expansions 时结束。

     但是我在使用时，故意造出了数十个以 swi 开头的词，而将 max_expansions 的值设为 10。但是却返回了所有的结果。在 elasitc 官网也有对该问题的讨论, 也是没有找到答案。这个问题作为一个公案权且记下，如果您知道原因，麻烦告诉我，非常感谢。

     这里也贴出个例子，以备后面排查
     **/
    private Integer maxExpansions;
}

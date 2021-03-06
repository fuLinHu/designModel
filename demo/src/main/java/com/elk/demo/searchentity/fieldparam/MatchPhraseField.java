package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.fieldparam.searchbasefield.SearchField;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/26 18:03
 * @Version V1.0
 */
@Data
@SuperBuilder
public class MatchPhraseField extends SearchField {
    //slop的含义是什么？
    //query string，搜索文本，中的几个term，要经过  最多几次移动才能与一个document匹配，这个移动的次数，就是slop
    private Integer slop;
    /**
     * @Author 付林虎
     * @Description //TODO  简单理解 "all" --- 停用词失效，全查， none ---  停用词 不被查到
     * @Param
     * @Version V1.0
     * @return
     **/
    private String zero_terms_query;

    private String analyzer;

}

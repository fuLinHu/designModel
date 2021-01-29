package com.elk.demo.searchentity.enumentity;

import com.elk.demo.elasticSearch.dao.search.*;
import lombok.Getter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 20:07
 * @Version V1.0
 */
@Getter
public enum SearchDataType {

    Combined(1,"combinedDao"),
    Dismax(2,"dismaxDao"),
    ElasticSearch(3,"elasticSearchDao"),
    Match(4,"matchDao"),
    MatchPhrase(5,"matchPhraseDao"),
    MultiMatch(6,"multiMatchDao"),
    Prefix(7,"prefixDao"),
    Range(8,"rangeDao"),
    Regexp(9,"regexpDao"),
    Term(10,"termDao"),
    Terms(11,"termsDao"),
    Wildcard(12,"wildcardDao");


    private String type;
    private Integer code;
    SearchDataType(Integer code,String type){
        this.code = code;
        this.type = type;
    }
}

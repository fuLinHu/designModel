package com.elk.demo.searchentity.result;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.elasticsearch.search.aggregations.Aggregation;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/2 11:08
 * @Version V1.0
 */
@Data
public class SearchResult {
    private List<JSONObject> searchResult;
    private AggResult aggResult;

    @Override
    public String toString() {
        return "{\"" +
                "searchResult\":\"" + searchResult +
                "\", \" aggResult\": \"" + aggResult + "\"}";
    }
}

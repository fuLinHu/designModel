package com.elk.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.elk.demo.searchentity.HighlightParam;
import com.elk.demo.searchentity.SearchParam;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;

import java.util.Map;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 19:32
 * @Version V1.0
 */
public class HighlighteUtil {
    public static String getHighLightContent(HighlightField highlightField){
        Text[] fragments = highlightField.getFragments();
        String highLightContent = "";
        for (Text fragment : fragments) {
            highLightContent+=fragment;
        }
        return highLightContent;
    }

    public static void parseHitToHighLight(SearchHit hit, JSONObject jsonObject){
        if(hit==null) return;
        Map<String, HighlightField> highlightFields = hit.getHighlightFields();
        if(highlightFields!=null&&highlightFields.size()>0){
            for (Map.Entry<String, HighlightField> stringHighlightFieldEntry : highlightFields.entrySet()) {
                String key = stringHighlightFieldEntry.getKey()+"_HighLight";
                HighlightField highlightField = stringHighlightFieldEntry.getValue();
                String highLightContent = HighlighteUtil.getHighLightContent(highlightField);
                jsonObject.put(key,highLightContent);
            }
        }
    }

    public static void paddingHighLightParamToSearch(SearchParam serachParam, SearchSourceBuilder searchSourceBuilder){
        HighlightParam highlightParam = serachParam.getHighlightParam();
        if(highlightParam!=null){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            HighlightParam.HiglightField[] higlightFields = highlightParam.getHiglightFields();
            if(higlightFields!=null&&higlightFields.length>0){
                for (HighlightParam.HiglightField higlightFieldParam : higlightFields) {

                    HighlightBuilder.Field highlightField =new HighlightBuilder.Field(higlightFieldParam.getHighField());

                    if (higlightFieldParam.getHighlighterType()!=null&&!"".equals(higlightFieldParam.getHighlighterType())) highlightField.highlighterType(higlightFieldParam.getHighlighterType());
                    if (higlightFieldParam.getFragment_offset()!=null) highlightField.fragmentOffset(higlightFieldParam.getFragment_offset());
                    if (higlightFieldParam.getFragment_size()!=null) highlightField.fragmentSize(higlightFieldParam.getFragment_size());
                    if (higlightFieldParam.getNo_match_size()!=null) highlightField.noMatchSize(higlightFieldParam.getNo_match_size());
                    if (higlightFieldParam.getNumber_of_fragments()!=null) highlightField.numOfFragments(higlightFieldParam.getNumber_of_fragments());

                    highlightBuilder.field(highlightField);
                }
            }
            String[] pre_tags = highlightParam.getPre_tags();
            if(pre_tags!=null&&pre_tags.length>0){
                highlightBuilder.preTags(pre_tags);
            }
            String[] post_tags = highlightParam.getPost_tags();
            if(post_tags!=null&&pre_tags.length>0){
                highlightBuilder.postTags(post_tags);
            }
            searchSourceBuilder.highlighter(highlightBuilder);
        }
    }
}

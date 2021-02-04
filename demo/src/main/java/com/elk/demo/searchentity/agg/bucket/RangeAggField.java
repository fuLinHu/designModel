package com.elk.demo.searchentity.agg.bucket;

import com.elk.demo.searchentity.agg.AggField;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.elasticsearch.index.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/4 9:01
 * @Version V1.0
 */
@SuperBuilder
@Data
public class RangeAggField extends AggField {
    //区间 -& a | a b | b c .... |n +&
    @Builder.Default
    private List<Section> sections = new ArrayList<>();

    public RangeAggField addSection(Double from,Double to){
        Section section = new Section(from, to);
        this.sections.add(section);
        return this;
    }
    public List<Section> toSections(){
        return this.sections;
    }
    @Data
    public static class Section{
        //上限
        private Double from;
        //下限
        private Double to;

        public Section(Double from,Double to){
            this.from = from;
            this.to = to;
        }
    }
}



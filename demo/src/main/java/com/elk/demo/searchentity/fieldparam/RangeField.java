package com.elk.demo.searchentity.fieldparam;

import com.elk.demo.searchentity.fieldparam.searchbasefield.SearchField;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/27 10:31
 * @Version V1.0
 */
@Data
@SuperBuilder
public class RangeField extends SearchField {
    private Float boost;

    //这四个底层最终都转化为  from  to
    private Object lt;
    private Object lte;
    private Object gt;
    private Object gte;

    //当使用 from to 这个api的时候  要添加 两个参数
    //"include_lower": false,  是否包含 右区间
    // "include_upper": true,  是否包含 左区间
    private FromToIfIncludeLowerUpper fromTo;
    @Data
    @Builder
    public static class FromToIfIncludeLowerUpper{
        private boolean include_lower;
        private boolean include_upper;
        private Object from;
        private Object to;
    }

}

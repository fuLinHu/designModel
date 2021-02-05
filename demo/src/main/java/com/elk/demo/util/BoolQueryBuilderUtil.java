package com.elk.demo.util;

import com.elk.demo.factory.QueryBuilderFactory;
import com.elk.demo.searchentity.enumentity.BoolQueryType;
import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/28 10:44
 * @Version V1.0
 */
public class BoolQueryBuilderUtil {
    public static void evaluationBool(BoolQueryBuilder boolQueryBuilder, Field field){
        BoolQueryType boolQueryType = field.getBoolQueryType();
        String type = boolQueryType.getType();
        QueryBuilder queryBuilder = QueryBuilderFactory.getQueryBuilder(field);
        if(BoolQueryType.SHOULD.getType().equals(type)){
            boolQueryBuilder.should(queryBuilder);
        }else if(BoolQueryType.MUST.getType().equals(type)){
            boolQueryBuilder.must(queryBuilder);
        }else if(BoolQueryType.MUST_NOT.getType().equals(type)){
            boolQueryBuilder.mustNot(queryBuilder);
        }else  if(BoolQueryType.FILTER.getType().equals(type)){
            boolQueryBuilder.filter(queryBuilder);
        }
    }

    public static BoolQueryBuilder evaluationBool( Field[] fields){
        if(fields==null||fields.length<=0){
            return null;
        }
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        for (Field field : fields) {
            evaluationBool(boolQueryBuilder,field);
        }
        return boolQueryBuilder;
    }

    public static void evaluationBool(BoolQueryBuilder boolQueryBuilder, Field[] fields){
        if(fields==null||fields.length<=0){
            throw new RuntimeException("fields 不能为空！");
        }
        for (Field field : fields) {
            evaluationBool(boolQueryBuilder,field);
        }
    }
}

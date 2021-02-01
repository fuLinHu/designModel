package com.elk.demo.factory;

import com.elk.demo.searchentity.enumentity.FieldType;
import com.elk.demo.searchentity.fieldparam.*;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.indices.TermsLookup;

import java.util.Arrays;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/27 9:07
 * @Version V1.0
 */

public class QueryBuilderFactory {

    private static QueryBuilder prefixQueryBuilder(Field field){
        PrefixQueryBuilder queryBuilder = new PrefixQueryBuilder(field.getFieldName(),field.getFieldValue()+"");
        return queryBuilder;
    }

    private static QueryBuilder regexpQueryBuilder(Field field){
        RegexpQueryBuilder queryBuilder = new RegexpQueryBuilder(field.getFieldName(),field.getFieldValue()+"");
        return queryBuilder;
    }

    private static QueryBuilder wildcardQueryBuilder(Field field){
        WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder(field.getFieldName(),field.getFieldValue()+"");
        return wildcardQueryBuilder;
    }

    private static QueryBuilder matchPhraseQueryBuilder(Field field){
        MatchPhraseField matchPhraseField = (MatchPhraseField)field;
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder(field.getFieldName(),field.getFieldValue());
        if (matchPhraseField.getSlop()!=null) matchPhraseQueryBuilder.slop(matchPhraseField.getSlop());
        if (matchPhraseField.getZero_terms_query()!=null&&!"".equals(matchPhraseField.getZero_terms_query())) matchPhraseQueryBuilder.zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(matchPhraseField.getZero_terms_query()));
        return matchPhraseQueryBuilder;
    }

    private static QueryBuilder matchQueryBuilder(Field field){
        MatchField matchField = (MatchField)field;
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(field.getFieldName(),field.getFieldValue());
        //后期仔细研究参数含义
        //matchQueryBuilder.fuzziness(0);
        if(matchField.getOperator()!=null&&!"".equals(matchField.getOperator())) matchQueryBuilder.operator(matchField.getOperator());
        if(matchField.getLenient()!=null) matchQueryBuilder.lenient(matchField.getLenient());
        if(matchField.getZero_terms_query()!=null&&!"".equals(matchField.getZero_terms_query())) matchQueryBuilder.zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(matchField.getZero_terms_query()));
        if(matchField.getMinimum_should_match()!=null&&!"".equals(matchField.getMinimum_should_match())) matchQueryBuilder.minimumShouldMatch(matchField.getMinimum_should_match());
        return matchQueryBuilder;
    }

    private static QueryBuilder termQueryBuilder(Field field){
        TermField termField = (TermField)field;
        boolean fieldTypeIfKeyWord = termField.isFieldTypeIfKeyWord();
        String fieldName = field.getFieldName();
        if(!fieldTypeIfKeyWord){
            fieldName = fieldName+".keyword";
        }
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder(fieldName, termField.getFieldValue());
        if (termField.getBoost()!=null) termQueryBuilder.boost(termField.getBoost());
        return termQueryBuilder;
    }

    private static QueryBuilder termsQueryBuilder(Field field){
        TermsField termsField = (TermsField)field;
        boolean fieldTypeIfKeyWord = termsField.isFieldTypeIfKeyWord();
        String fieldName = field.getFieldName();
        if(!fieldTypeIfKeyWord){
            fieldName = fieldName+".keyword";
        }
        Object fieldValue = termsField.getFieldValue();
        TermsLookup termsLookup = termsField.getTermsLookup();
        TermsQueryBuilder termsQueryBuilder =null;
        if(termsLookup!=null){
            List<Object> objects = Arrays.asList(fieldValue);
            termsQueryBuilder = new TermsQueryBuilder(fieldName, objects,termsLookup);
        }else{
            termsQueryBuilder = new TermsQueryBuilder(fieldName,  termsField.getFieldValue());
        }
        if (termsField.getBoost()!=null) termsQueryBuilder.boost(termsField.getBoost());
        return termsQueryBuilder;
    }

    private static QueryBuilder rangeQueryBuilder(Field field){
        RangeField rangeField = (RangeField)field;
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder(field.getFieldName());
        if(rangeField.getBoost()!=null) rangeQueryBuilder.boost(rangeField.getBoost());
        RangeField.FromToIfIncludeLowerUpper fromTo = rangeField.getFromTo();
        if(fromTo!=null) rangeQueryBuilder.from(fromTo.getFrom()).to(fromTo.getTo()).includeLower(fromTo.isInclude_lower()).includeUpper(fromTo.isInclude_upper());
        if(rangeField.getGt()!=null) rangeQueryBuilder.gt(rangeField.getGt());
        if(rangeField.getGte()!=null) rangeQueryBuilder.gte(rangeField.getGte());
        if(rangeField.getLt()!=null) rangeQueryBuilder.lt(rangeField.getLt());
        if(rangeField.getLte()!=null) rangeQueryBuilder.lte(rangeField.getLte());
        return rangeQueryBuilder;

    }

    private static  QueryBuilder multiMatchQueryBuilder(Field field){
        MultiMatchField multiMatchField = (MultiMatchField)field;
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(multiMatchField.getFieldValue(), multiMatchField.getFieldNames());
        if(multiMatchField.getType()!=null) multiMatchQueryBuilder.type(multiMatchField.getType());
        if(multiMatchField.getTie_breaker()!=null) multiMatchQueryBuilder.tieBreaker(multiMatchField.getTie_breaker());
        if(multiMatchField.getLenient()!=null) multiMatchQueryBuilder.lenient(multiMatchField.getLenient());
        if(multiMatchField.getMinimum_should_match()!=null) multiMatchQueryBuilder.minimumShouldMatch(multiMatchField.getMinimum_should_match());
        if(multiMatchField.getOperator()!=null) multiMatchQueryBuilder.operator(multiMatchField.getOperator());
        return multiMatchQueryBuilder;
    }

    private static QueryBuilder nestedQueryBuilder(Field field){
        NestedField nestedField = (NestedField)field;
        ScoreMode scoreMode = nestedField.getScoreMode() != null ? ScoreMode.Avg : nestedField.getScoreMode();
        if(nestedField.getBoolQueryBuilder()==null) throw new RuntimeException("nestedQueryBuilder 构造函数 参数  QueryBuilder query 不能为空！！ ");
        NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder(nestedField.getPath(),nestedField.getBoolQueryBuilder(),scoreMode);
        return nestedQueryBuilder;
    }

    public static QueryBuilder getQueryBuilder(Field field){
        FieldType fieldType = field.getFieldType();
        if(field instanceof MatchField){
            return matchQueryBuilder(field);
        }else if(field instanceof MatchPhraseField){
            return matchPhraseQueryBuilder(field);
        }else if(field instanceof RangeField){
            return rangeQueryBuilder(field);
        }else if(field instanceof MultiMatchField){
            return multiMatchQueryBuilder(field);
        }else if(field instanceof TermField){
            return termQueryBuilder(field);
        }else if(field instanceof TermsField){
            return termsQueryBuilder(field);
        }else if(field instanceof NestedField){
            return nestedQueryBuilder(field);
        }
        else if(fieldType!=null){
            if(FieldType.PREFIX.equals(fieldType.getType())) return prefixQueryBuilder(field);
            if(FieldType.REGEXP.equals(fieldType.getType())) return regexpQueryBuilder(field);
            if(FieldType.WILDCARD.equals(fieldType.getType())) return wildcardQueryBuilder(field);
            throw new RuntimeException("需要查询的字段类【"+field.getClass().getName()+"】没有对应的实现，且者是：FieldType 找不到对应的枚举:"+fieldType.getType());
        }
        throw new RuntimeException("需要查询的字段类【"+field.getClass().getName()+"】没有对应的实现，或者：类 FieldType 为null");
    }






}

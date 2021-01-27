package com.elk.demo.elasticSearch.factory;

import com.elk.demo.searchentity.fieldparam.*;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQuery;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/27 9:07
 * @Version V1.0
 */

public class QueryBuilderFactory {

    public static QueryBuilder prefixQueryBuilder(Field field){
        PrefixQueryBuilder queryBuilder = new PrefixQueryBuilder(field.getFieldName(),field.getFieldValue()+"");
        return queryBuilder;
    }

    public static QueryBuilder regexpQueryBuilder(Field field){
        RegexpQueryBuilder queryBuilder = new RegexpQueryBuilder(field.getFieldName(),field.getFieldValue()+"");
        return queryBuilder;
    }

    public static QueryBuilder wildcardQueryBuilder(Field field){
        WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder(field.getFieldName(),field.getFieldValue()+"");
        return wildcardQueryBuilder;
    }

    public static QueryBuilder matchPhraseQueryBuilder(Field field){
        MatchPhraseField matchPhraseField = (MatchPhraseField)field;
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder(field.getFieldName(),field.getFieldValue());
        if (matchPhraseField.getSlop()!=null) matchPhraseQueryBuilder.slop(matchPhraseField.getSlop());
        if (matchPhraseField.getZero_terms_query()!=null&&!"".equals(matchPhraseField.getZero_terms_query())) matchPhraseQueryBuilder.zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(matchPhraseField.getZero_terms_query()));
        return matchPhraseQueryBuilder;
    }

    public static QueryBuilder matchQueryBuilder(Field field){
        MatchField matchField = (MatchField)field;
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(field.getFieldName(),field.getFieldValue());

        //后期仔细研究参数含义
        //matchQueryBuilder.fuzziness(0);
        if(matchField.getOperator()!=null&&!"".equals(matchField.getOperator())) matchQueryBuilder.operator(Operator.fromString(matchField.getOperator()));
        if(matchField.getLenient()!=null) matchQueryBuilder.lenient(matchField.getLenient());
        if(matchField.getZero_terms_query()!=null&&!"".equals(matchField.getZero_terms_query())) matchQueryBuilder.zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(matchField.getZero_terms_query()));
        if(matchField.getMinimum_should_match()!=null&&!"".equals(matchField.getMinimum_should_match())) matchQueryBuilder.minimumShouldMatch(matchField.getMinimum_should_match());
        return matchQueryBuilder;
    }

    public static QueryBuilder termQueryBuilder(Field field){
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

    public static QueryBuilder termsQueryBuilder(Field field){
        TermField termField = (TermField)field;
        boolean fieldTypeIfKeyWord = termField.isFieldTypeIfKeyWord();
        String fieldName = field.getFieldName();
        if(!fieldTypeIfKeyWord){
            fieldName = fieldName+".keyword";
        }
        TermsQueryBuilder termsQueryBuilder = new TermsQueryBuilder(fieldName, termField.getFieldValue());
        if (termField.getBoost()!=null) termsQueryBuilder.boost(termField.getBoost());
        return termsQueryBuilder;
    }

    public static QueryBuilder rangeQueryBuilder(Field field){
        RangeField rangeField = (RangeField)field;
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder(field.getFieldName());
        if(rangeField.getBoost()!=null) rangeQueryBuilder.boost(rangeField.getBoost());
        if(rangeField.getFrom()!=null) rangeQueryBuilder.from(rangeField.getFrom());
        if(rangeField.getTo()!=null) rangeQueryBuilder.to(rangeField.getTo());
        if(rangeField.getGt()!=null) rangeQueryBuilder.gt(rangeField.getGt());
        if(rangeField.getGte()!=null) rangeQueryBuilder.gte(rangeField.getGte());
        if(rangeField.getLt()!=null) rangeQueryBuilder.lt(rangeField.getLt());
        if(rangeField.getLte()!=null) rangeQueryBuilder.lte(rangeField.getLte());
        return rangeQueryBuilder;
    }






}

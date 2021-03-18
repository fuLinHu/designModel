package com.elk.demo.factory;

import com.elk.demo.searchentity.fieldparam.*;
import com.elk.demo.searchentity.fieldparam.searchbasefield.Field;
import com.elk.demo.searchentity.fieldparam.searchbasefield.SearchField;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.search.MatchQuery;
import org.elasticsearch.indices.TermsLookup;
import org.elasticsearch.search.rescore.RescorePhase;

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
        SearchField searchField = (PrefixField)field;
        PrefixQueryBuilder queryBuilder = new PrefixQueryBuilder(searchField.getFieldName(),searchField.getFieldValue()+"");
        return queryBuilder;
    }

    private static QueryBuilder regexpQueryBuilder(Field field){
        SearchField searchField = (RegexpField)field;
        RegexpQueryBuilder queryBuilder = new RegexpQueryBuilder(searchField.getFieldName(),searchField.getFieldValue()+"");
        return queryBuilder;
    }

    private static QueryBuilder wildcardQueryBuilder(Field field){
        SearchField searchField = (SearchField)field;
        WildcardQueryBuilder wildcardQueryBuilder = new WildcardQueryBuilder(searchField.getFieldName(),searchField.getFieldValue()+"");
        return wildcardQueryBuilder;
    }

    private static QueryBuilder matchPhrasePrefixField(Field field){
        MatchPhrasePrefixField matchPhrasePrefixField = (MatchPhrasePrefixField)field;
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery(matchPhrasePrefixField.getFieldName(), matchPhrasePrefixField.getFieldValue());
        if(matchPhrasePrefixField.getAnalyzer()!=null)matchPhrasePrefixQueryBuilder.analyzer(matchPhrasePrefixField.getAnalyzer());
        if(matchPhrasePrefixField.getMaxExpansions()!=null) matchPhrasePrefixQueryBuilder.maxExpansions(matchPhrasePrefixField.getMaxExpansions());
        return matchPhrasePrefixQueryBuilder;
    }


    private static QueryBuilder matchPhraseQueryBuilder(Field field){
        SearchField searchField = (MatchPhraseField)field;
        MatchPhraseField matchPhraseField = (MatchPhraseField)field;
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = new MatchPhraseQueryBuilder(searchField.getFieldName(),searchField.getFieldValue());
        if (matchPhraseField.getSlop()!=null) matchPhraseQueryBuilder.slop(matchPhraseField.getSlop());
        if (matchPhraseField.getZero_terms_query()!=null&&!"".equals(matchPhraseField.getZero_terms_query())) matchPhraseQueryBuilder.zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(matchPhraseField.getZero_terms_query()));
        if (matchPhraseField.getAnalyzer()!=null&&!"".equals(matchPhraseField.getAnalyzer()))matchPhraseQueryBuilder.analyzer(matchPhraseField.getAnalyzer());

        return matchPhraseQueryBuilder;
    }

    private static QueryBuilder matchQueryBuilder(Field field){
        MatchField matchField = (MatchField)field;
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder(matchField.getFieldName(),matchField.getFieldValue());
        //后期仔细研究参数含义
        //matchQueryBuilder.fuzziness(0);

        //todo fuzziness 纠错   比如 hello 是正确的，如果 你用hallo去搜索   如果matchQueryBuilder.fuzziness("AUTO"); 则会匹配到hello
        //GET /my_index/my_type/_search
        //{
        //  "query": {
        //    "fuzzy": {
        //      "text": {
        //        "value": "surprize",
        //        "fuzziness": 2
        //      }
        //    }
        //  }
        //}
        //
        //surprize --> 拼写错误 --> surprise --> s -> z
        //
        //surprize --> surprise -> z -> s，纠正一个字母，就可以匹配上，所以在fuziness指定的2范围内
        //surprize --> surprised -> z -> s，末尾加个d，纠正了2次，也可以匹配上，在fuziness指定的2范围内
        //surprize --> surprising -> z -> s，去掉e，ing，3次，总共要5次，才可以匹配上，始终纠正不了
        matchQueryBuilder.fuzziness("AUTO");
        if(matchField.getOperator()!=null&&!"".equals(matchField.getOperator())) matchQueryBuilder.operator(matchField.getOperator());
        if(matchField.getLenient()!=null) matchQueryBuilder.lenient(matchField.getLenient());
        if(matchField.getZero_terms_query()!=null&&!"".equals(matchField.getZero_terms_query())) matchQueryBuilder.zeroTermsQuery(MatchQuery.ZeroTermsQuery.valueOf(matchField.getZero_terms_query()));
        if(matchField.getMinimum_should_match()!=null&&!"".equals(matchField.getMinimum_should_match())) matchQueryBuilder.minimumShouldMatch(matchField.getMinimum_should_match());
        if (matchField.getAnalyzer()!=null&&!"".equals(matchField.getAnalyzer()))matchQueryBuilder.analyzer(matchField.getAnalyzer());
        return matchQueryBuilder;
    }

    private static QueryBuilder termQueryBuilder(Field field){
        TermField termField = (TermField)field;
        boolean fieldTypeIfKeyWord = termField.isFieldTypeIfKeyWord();
        String fieldName = termField.getFieldName();
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
        String fieldName = termsField.getFieldName();
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
        RangeQueryBuilder rangeQueryBuilder = new RangeQueryBuilder(rangeField.getFieldName());
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
        NestedField field1 = (NestedField)field;
        ScoreMode scoreMode = field1.getScoreMode() == null ? ScoreMode.Avg : field1.getScoreMode();
        if(field1.getBoolQueryBuilder()==null) throw new RuntimeException("nestedQueryBuilder 构造函数 参数  QueryBuilder query 不能为空！！ ");
        NestedQueryBuilder nestedQueryBuilder = new NestedQueryBuilder(field1.getPath(),field1.getBoolQueryBuilder(),scoreMode);
        return nestedQueryBuilder;
    }

    private static QueryBuilder idsQueryBuilder(Field field){
        IdsField field1 = (IdsField)field;
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
        idsQueryBuilder.addIds(field1.getIds());
        return idsQueryBuilder;
    }

    private static QueryBuilder existsQueryBuilder(Field field){
        ExistsField field1 = (ExistsField)field;
        ExistsQueryBuilder existsQueryBuilder = QueryBuilders.existsQuery(field1.getFieldName());
        return existsQueryBuilder;
    }

    private static QueryBuilder matchAllQueryBuilder(Field field){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();

        return matchAllQueryBuilder;
    }

    /**
     * @Author 付林虎
     * @Description //TODO  对字符串查询得封装  后期在丰富
     * @Date 2021/2/5 14:33
     * @Param [field]
     * @Version V1.0
     * @return org.elasticsearch.index.query.QueryBuilder
     **/
    private static QueryBuilder queryStringQueryBuilder(Field field){
        QueryStringField field1 = (QueryStringField)field;
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery(field1.getFieldValue()+"");
        if(field1.getFieldsMap().size()>0)queryStringQueryBuilder.fields(field1.getFieldsMap());
        if(field1.getAnalyzer()!=null&&!"".equals(field1.getAnalyzer())) queryStringQueryBuilder.analyzer(field1.getAnalyzer());
        return queryStringQueryBuilder;
    }



    //-------------------该方法要修改，如果新增 一种查询！---------------------//

    public static QueryBuilder getQueryBuilder(Field field){
        QueryBuilder queryBuilder =null;
        if(field instanceof MultiMatchField){
            queryBuilder =  multiMatchQueryBuilder(field);
        }else if(field instanceof MatchField){
            queryBuilder =  matchQueryBuilder(field);
        }else if(field instanceof MatchPhraseField){
            queryBuilder =  matchPhraseQueryBuilder(field);
        }else if(field instanceof RangeField){
            queryBuilder =  rangeQueryBuilder(field);
        }else if(field instanceof TermsField){
            queryBuilder =  termsQueryBuilder(field);
        }else if(field instanceof TermField){
            queryBuilder =  termQueryBuilder(field);
        }else if(field instanceof NestedField){
            queryBuilder =  nestedQueryBuilder(field);
        }else if(field instanceof PrefixField){
            queryBuilder =  prefixQueryBuilder(field);
        }else if(field instanceof RegexpField){
            queryBuilder =  regexpQueryBuilder(field);
        }else if(field instanceof WildcardField){
            queryBuilder =  wildcardQueryBuilder(field);
        }else if(field instanceof IdsField){
            queryBuilder =  idsQueryBuilder(field);
        }else if(field instanceof ExistsField){
            queryBuilder =  existsQueryBuilder(field);
        }else if(field instanceof MatchAllField){
            queryBuilder =  matchAllQueryBuilder(field);
        }else if(field instanceof MatchPhrasePrefixField){
            queryBuilder =  matchPhrasePrefixField(field);
        }else if(field instanceof QueryStringField){
            queryBuilder = queryStringQueryBuilder(field);
        }
        if (queryBuilder==null) throw new RuntimeException("需要查询的字段类【"+field.getClass().getName()+"】没有对应的实现，或者：类 FieldType 为null");
        if(field.getBoost()!=null) queryBuilder.boost(field.getBoost());
        return queryBuilder;
    }






}

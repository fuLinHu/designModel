package com.elk.demo.searchentity.enumentity;

import lombok.Getter;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/1/29 9:02
 * @Version V1.0
 */
@Getter
public enum  SearchType {

    //query_and_fetch
    //query_then_fetch
    //dfs_query_and_fetch
    //dfs_query_then_fetch
    QUERY_AND_FETCH(1,"query_and_fetch"),
    QUERY_THEN_FETCH(1,"query_then_fetch"),
    DFS_QUERY_AND_FETCH(1,"dfs_query_and_fetch"),
    DFS_QUERY_THEN_FETCH(1,"dfs_query_then_fetch");

    private String type;
    private Integer code;
    SearchType(Integer code,String type){
        this.code = code;
        this.type = type;
    }
}

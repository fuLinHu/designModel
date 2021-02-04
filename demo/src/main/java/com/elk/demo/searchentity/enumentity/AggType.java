package com.elk.demo.searchentity.enumentity;

import lombok.Getter;

@Getter
public enum AggType {
    AVG(1,"avg"),
    MIN(2,"min"),
    MAX(3,"max"),
    SUM(4,"sum"),
    VALUE_COUNT(5,"value_count"),
    CARDINALITY(6,"cardinality"),
    STATS(7,"stats"),
    EXTENDED_STATS(8,"extended_stats"),
    PERCENTILES(9,"percentiles"),
    PERCENTILE_RANKS(10,"percentile_ranks"),
    TOP_HITS(11,"top_hits"),
    GEO_BOUNDS(12,"geo_bounds"),
    GEO_CENTROID(13,"geo_centroid");

    private Integer code;
    private String type;
    AggType(Integer code,String type){
        this.code=code;
        this.type = type;
    }
}

package com.bigdata.flink.study;

import lombok.Data;
import org.apache.flink.api.java.tuple.Tuple;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/10 11:26
 * @Version V1.0
 */
@Data
public class SensorReading extends Tuple {
    private String zb;
    private double x;
    private double y;

    public SensorReading(String zb, double x, double y) {
        this.zb = zb;
        this.x = x;
        this.y = y;
    }

    @Override
    public <T> T getField(int i) {
        return null;
    }

    @Override
    public <T> void setField(T t, int i) {

    }

    @Override
    public int getArity() {
        return 0;
    }

    @Override
    public <T extends Tuple> T copy() {
        return null;
    }
}

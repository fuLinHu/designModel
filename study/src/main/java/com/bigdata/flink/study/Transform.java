package com.bigdata.flink.study;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/10/9 11:33
 * @Version V1.0
 */
public class Transform {
    public static void main(String[] args) throws Exception {
        //1.获取执行环境(execution environment)
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //读取数据（文件读取）
        DataStreamSource<String> text  = env.readTextFile("E:\\javacode\\javamodel\\designModel\\study\\src\\main\\resources\\word.txt");
       /* SingleOutputStreamOperator<Tuple2<SensorReading, String>> sum1 = text.map(line -> {
            String[] split = line.split(",");
            Tuple2<SensorReading, String> of = Tuple2.of(new SensorReading(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2])), split[0]);
            return of;
        }).returns(Types.TUPLE(TypeInformation.of(SensorReading.class), Types.STRING)).keyBy(1).max(1);*/

/*
        KeyedStream<Object, Tuple> objectTupleKeyedStream = text.map((MapFunction<String, Object>) s -> {

            String[] split = s.split(",");
            SensorReading sensorReading = new SensorReading(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]));
            return sensorReading;
        }).keyBy(0);
        SingleOutputStreamOperator<Object> sum = objectTupleKeyedStream.sum(2);*/
        /*KeyedStream<SensorReading, Tuple> zb = text.map((MapFunction<String, SensorReading>) s -> {

            String[] split = s.split(",");
            SensorReading sensorReading = new SensorReading(split[0], Double.parseDouble(split[1]), Double.parseDouble(split[2]));
            return sensorReading;
        }).keyBy("zb");
        SingleOutputStreamOperator<SensorReading> sum = zb.sum("x");*/

        /*SingleOutputStreamOperator<Tuple3<String, Double, Double>> map = text.map(line -> {
            String[] split = line.split(",");
            Tuple3<String, Double, Double> of = Tuple3.of(split[0],Double.parseDouble(split[1]) , Double.parseDouble(split[2]));
            return of;
        }).returns(Types.TUPLE(Types.STRING, Types.DOUBLE,Types.DOUBLE)).keyBy(0).sum(1);
        map.print("这个是我的测试数据");
        env.execute("=====Transform test====");*/
        Date date = new Date();
        long time = date.getTime();
        System.out.println("time = " + time);


    }
}

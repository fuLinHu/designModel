package com.study.javamodel.lamdba;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/4 15:27
 * @Version V1.0
 */
public class TestMethod {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(1);


        Collections.sort(list,(o1,o2)->{
            return o1-o2;
        });

        System.out.println(JSONArray.fromObject(list));
    }
}




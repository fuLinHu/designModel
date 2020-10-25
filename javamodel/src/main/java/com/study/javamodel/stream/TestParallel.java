package com.study.javamodel.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/9/11 11:46
 * @Version V1.0
 */
@Slf4j
public class TestParallel {
    public static void main(String[] args) {
        String[] arr = new String[]{"小红1","小红2","小红3","小红4","小红5","小红6","小红7","小红8","小红4","小红5","小红1","小红2","小红3","小红4","小红5"};
        Arrays.stream(arr).parallel().forEach(o->{
            log.info("运行到：："+o);
        });
    }
}

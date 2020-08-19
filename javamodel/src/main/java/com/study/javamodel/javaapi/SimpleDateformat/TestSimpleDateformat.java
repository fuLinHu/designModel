package com.study.javamodel.javaapi.SimpleDateformat;

import javautil.DateFormatUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/8/19 16:52
 * @Version V1.0
 */
public class TestSimpleDateformat {



    public static void main(String[] args) {
        //test2();
        System.out.println(DateFormatUtil.convertDate2Str("yyyy-MM-dd HH:mm:ss", new Date().getTime()));
    }

    public static void test1(){
        for (int i = 0; i <1000 ; i++) {
            new Thread(()->{
                DateFormat dateFormat = DateFormatUtil.getDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parse = null;
                try {
                    parse = dateFormat.parse("2020-07-08 00:00:00");
                } catch (ParseException e) {
                    e.printStackTrace();
                }finally {
                    DateFormatUtil.remove();
                }
                System.out.println(parse.toString());
            }).start();
        }
    }




    public static void test2(){
        for (int i = 0; i <1000 ; i++) {
            new Thread(()->{
                Date date = DateFormatUtil.convertStr2Date("2020-07-08 00:00:00", "yyyy-MM-dd hh:mm:ss", ZoneIdEnum.ASIA_SHANGHAI);
                System.out.println(date.toString());
            }).start();
        }
    }



}

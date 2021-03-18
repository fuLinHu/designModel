package com.study.javamodel.arithmetic;

import java.util.*;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/3/3 8:31
 * @Version V1.0
 */
public class Arithmetic1 {
    /**
     公司负责某 APP 测试版本编写的前端程序员是一个斐波那契数的爱好者（斐波那契序列是数字序列：0、1、1、2、3、5、8、13、21、34、...下一个数字是通过将前两个数字加起来得到的：首个0和1是基数，后面如： 5 = 2 + 3； 21 = 8 + 13）。

     内测时，用户可以自定义用户名，用户名仅由小写字母组成。作为一个彩蛋，如果这个用户名是一个给定字符串 s 的子字符串，而且用户名中的不同字符的数目为斐波那契数时，用户登录成功后，界面会显示 LUCKY 字样。

     给定字符串 s ，请你按照字典序输出所有能在登录时显示出 LUCKY 的字符串。输出的字符串不能重复。

     示例 1：

     输入：s = "aabcd"
     输出：？
     **/

    public static void main(String[] args) {
        String s = "aabcd";
        printLUCKY(s);
    }

    public static void printLUCKY(String str){
       Map<String,String> map= new TreeMap<>();
        getSonStr(str,map);
        if(map.size()>0){
            for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
                String key = stringStringEntry.getKey();
                boolean b = ifFibonacci(key.length());
                if(b){
                    System.out.println("给定字符串为：【"+str+"】  其符合要求的LUCKY字符串：【"+key+"】");
                }
            }
        }
    }

    public static boolean ifFibonacci(int  num){
        int a=0,b=1,n;
        while(b<num){
            n=a+b;
            a=b;
            b=n;
        }
        if(num==b||num == 0){
            return true;
        } else {
            return false;
        }
    }


    public static void  getSonStr(String a, Map<String,String> treeMap){
        String sub1 = "";
        String sub2 = "";
        if (a.length()==1) {
            //字符串长度为1不需要在进行切割
            treeMap.put(a,null);//输入长度唯一时直接添加进set
        }else{
            for(int i =1;i<a.length();i++){
                sub1 = a.substring(i);
                sub2 = a.substring(0,i);
                treeMap.put(sub1,null);
                treeMap.put(sub2,null);
                getSonStr(sub1,treeMap);//切割的两个字符串进行递归
                getSonStr(sub2,treeMap);//同上
            }
        }
    }



}

package javautil;

import java.io.IOException;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/8/16 15:58
 * @Param $
 * @return $
 * @Version V1.0
 */
public class VbaRun {
    public static void main(String[] args) {
        try {
            String[] cpCmd  = new String[]{"wscript", "E:\\项目文件(北明智通)\\外高桥\\文档\\分页\\parseword.vbs"};
            Process process = Runtime.getRuntime().exec(cpCmd);
            int val = process.waitFor();//val 是返回值，如果返回0，那么正常执行。
            System.out.println(val);
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}

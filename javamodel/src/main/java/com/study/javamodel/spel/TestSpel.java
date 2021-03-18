package com.study.javamodel.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2021/2/8 8:25
 * @Version V1.0
 */
public class TestSpel {

    private static String qiyebiaozhun = "#{ '#lingyu' matches '.*检测技术.*' && ('#zhuti' matches '.*检测标准规范/计量检定规程.*'||'#zhuti' matches '.* 检测标准规范/理化检验标准.*') ? '企业标准' : '' }";
    private static String qiyebiaozhun1 = "((#lingyu == '检测技术' && #zhuti matches '.*检测标准规范.*')||(#lingyu == '标准规范' && #zhuti matches '.*企业标准.*'))";
    private static String qiyebiaozhun1_1 = "(#lingyu matches '.*检测技术.*' && (#zhuti matches '.*检测标准规范/计量检定规程.*'||#zhuti matches '.* 检测标准规范/理化检验标准.*')) ? '企业标准':''";
    private static String qiyebiaozhun2 = "'#lingyu' matches '.*检测技术.*'";
    public static void main(String[] args) throws NoSuchMethodException {
//        ExpressionParser parser = new SpelExpressionParser();
//       /* EvaluationContext context = new StandardEvaluationContext();  // 表达式的上下文,
//        context.setVariable("zhuti", "你好");    // 为了让表达式可以访问该对象, 先把对象放到上下文中
//        context.setVariable("templateAttrMingCheng", "88你好");
//        //Expression expression = parser.parseExpression("#zhuti matches '.*#templateAttrMingCheng.*'");
//        Expression  expression = parser.parseExpression("#zhuti matches '.*'#templateAttrMingCheng'.*'");
//        Object value = expression.getValue(context);*/
//        EvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("zhuti", "你好888");    // 为了让表达式可以访问该对象, 先把对象放到上下文中
//        context.setVariable("mingcheng", "你好1");
//        context.setVariable("tongyici", "你好");
//        //Expression  expression = parser.parseExpression("'#{#zhuti}'.contains('你好')");
//        //(#zhuti.contains(#mingcheng)||#zhuti.contains(#tongyici))?#mingcheng:''
//        //Expression  expression =  parser.parseExpression("(#zhuti.contains(#mingcheng)||#zhuti.contains(#tongyici))?#mingcheng:''");
//        String str = "#tongyici.split(';')";
//        Expression  expression =  parser.parseExpression(str);
//        Object value = expression.getValue(context);
//        System.out.println(value);



        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("ifContains",
                TestSpel.class.getDeclaredMethod("ifContains", String.class,String.class));
        context.setVariable("tongyici","你好;你好");
        context.setVariable("zhuti","你好");
        context.setVariable("mingcheng","你好2");

        Expression  expression =  parser.parseExpression("(#zhuti.contains(#mingcheng)||#ifContains(#tongyici,#zhuti))?#mingcheng:''");
        Object value = expression.getValue(context);
        /*String helloWorldReversed = parser.parseExpression(
                "#ifContains(#tongyici,#zhuti)").getValue(context, String.class);*/
        System.out.println(value);
        /*ExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("'hello'").getValue(String.class));
        System.out.println(parser.parseExpression("1.024E+3").getValue(Long.class));
        System.out.println(parser.parseExpression("0xFFFF").getValue(Integer.class));
        System.out.println(parser.parseExpression("true").getValue(Boolean.class));
        System.out.println(parser.parseExpression("null").getValue());
        System.out.println("==========================================================");
        // 定义变量
        String name = "Tom";
        EvaluationContext context = new StandardEvaluationContext();  // 表达式的上下文,
        context.setVariable("myName", name);                        // 为了让表达式可以访问该对象, 先把对象放到上下文中
        // 访问变量
        System.out.println(parser.parseExpression("#myName").getValue(context, String.class));
        // 直接使用构造方法创建对象
        System.out.println(parser.parseExpression("new String('aaa')").getValue(String.class));
        System.out.println(parser.parseExpression("'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class));
        System.out.println(parser.parseExpression("'5.00' matches ''").getValue(Boolean.class));
        contains("付林虎","林");*/
        //arse(qiyebiaozhun1,"uuufafd检测技术芬撒发","检测标准规范/计量检定规程");
    }

    private static void parse(String guize,String lingyu,String zhuti){
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();  // 表达式的上下文,
        context.setVariable("lingyu", lingyu);
        context.setVariable("zhuti", zhuti);    // 为了让表达式可以访问该对象, 先把对象放到上下文中
        Expression expression = parser.parseExpression(guize);
        Object value = expression.getValue(context);

        System.out.println(value);
    }

    private static Boolean contains(String str,String pattern){
        ExpressionParser parser = new SpelExpressionParser();
        Boolean value = parser.parseExpression("'" + str + "' matches '.*" + pattern + ".*'").getValue(Boolean.class);
        System.out.println("contains -------"+value);
        return value;
    }





    private static boolean ifContains(String tongyici,String zhuti) {
        String[] split = tongyici.split(";");
        for (String s : split) {
            if(zhuti.contains(s)){
                return true;
            }
        }
        return false;
    }
}

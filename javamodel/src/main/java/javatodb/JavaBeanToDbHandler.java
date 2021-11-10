package javatodb;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/10 10:33
 * @Version V1.0
 */
@Component
public class JavaBeanToDbHandler implements BeanPostProcessor {

    @Resource
    private DbService dbService;
    @Value("${javatodb.ifrun}")
    private boolean ifrun;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!ifrun){
            return bean;
        }
        Class<?> aClass = bean.getClass();
        if(aClass.isAnnotationPresent(JAVABeanToDB.class)){
            JAVABeanToDB annotation = aClass.getAnnotation(JAVABeanToDB.class);
            if(annotation!=null){
                String tableName = annotation.tableName();
                if(tableName==null||"".equals(tableName)){
                    tableName = aClass.getSimpleName();
                }
                Map<String, Set<String>> tablesMap = dbService.getTablesMap();
                if(!tablesMap.containsKey(tableName)){

                    StringBuffer stringBuffer = new StringBuffer ();
                    stringBuffer.append ("CREATE TABLE `"+tableName+"` (");
                    Field[] declaredFields = bean.getClass().getDeclaredFields();
                    String keySql = "";
                    for (Field declaredField : declaredFields) {
                        Class<?> type = declaredField.getType();
                        String name = "";
                        JAVABeanToDB annotation1 = declaredField.getAnnotation(JAVABeanToDB.class);
                        String mysqlColunmType = getMysqlColunmType(type,annotation1);
                        boolean b = false;
                        if(annotation1!=null){
                            name = annotation1.dbField();
                            b = annotation1.iftableId();
                            if(b){
                                keySql =  "PRIMARY KEY (`"+name+"`) USING BTREE";
                                stringBuffer.append ("\n").append (name+" ");
                                stringBuffer.append (mysqlColunmType);
                                stringBuffer.append (" NOT NULL AUTO_INCREMENT ,");
                            }
                        }else{
                            name = getName(declaredField.getName());
                        }
                        if("serialVersionUID".equals(name)){
                            continue;
                        }
                        if(!b){
                            stringBuffer.append ("\n").append (name+" ");
                            stringBuffer.append (mysqlColunmType);
                            stringBuffer.append (" NULL DEFAULT NULL ,");
                        }

                    }
                    if(keySql==null||"".equals(keySql)){
                        stringBuffer.delete (stringBuffer.length ()-1,stringBuffer.length ());
                    }else{
                        stringBuffer.append("\n").append(keySql);
                    }
                    stringBuffer.append ("\n").append (")ENGINE=InnoDB\n AUTO_INCREMENT = 1 " +
                            "DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci ;");
                    dbService.createTable(stringBuffer.toString());
                    String[] table = new String[]{tableName};
                    if(ifrun){
                        GenersqlClient.genertMybatis(table);
                    }
                }else{
                    Set<String> strings = tablesMap.get(tableName);
                    Field[] declaredFields = bean.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        Class<?> type = declaredField.getType();
                        JAVABeanToDB annotation1 = declaredField.getAnnotation(JAVABeanToDB.class);
                        String mysqlColunmType = getMysqlColunmType(type,annotation1);
                        String fieldName = "";
                        if(annotation1!=null){
                            fieldName = annotation1.dbField();
                        }else{
                            fieldName = declaredField.getName();
                        }
                        if("serialVersionUID".equals(fieldName)){
                            continue;
                        }
                        if(!strings.contains(fieldName)){
                           String sql = "alter table "+tableName+" add "+fieldName +" "+mysqlColunmType+" NULL DEFAULT NULL;";
                            dbService.createTable(sql);
                        }
                        //alter table student add name varchar(64) not null;
                    }

                }

            }
        }
        return bean;
    }

    public static String getMysqlColunmType(Class cls ,JAVABeanToDB annotation1){
        String clsName = cls.toString ();
        if (cls == Date.class){
            return  "datetime";
        }
        if (cls == BigDecimal.class){
            return  "decimal(12,2)";
        }
        if (cls == Integer.class||cls == Long.class || clsName.equals ("int") || clsName.equals ("long")){
            return  "int";
        }
        //double(11, 0)
        if(cls == Double.class||cls == Float.class || clsName.equals ("double") || clsName.equals ("float")){
            return  "double";
        }
        if(annotation1!=null){
            return "varchar("+annotation1.length()+") CHARACTER SET utf8 COLLATE utf8_general_ci ";
        }
        return "varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci ";
    }

    private  static String getName(String name){
        StringBuffer stringBuffer = new StringBuffer ();
        for (char c : name.toCharArray ()) {
            if (Character.isUpperCase (c)){
                stringBuffer.append ("_");
                stringBuffer.append (Character.toLowerCase (c));
            }else{
                stringBuffer.append (c);
            }
        }
        return stringBuffer.toString ();
    }


}

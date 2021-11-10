package javatodb;


import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/10 11:28
 * @Version V1.0
 */
@Component
@Slf4j
public class DbService {

    @Getter
    private Map<String, Set<String>> tablesMap = new HashMap<>();
    @Resource
    private HikariDataSource hikariDataSource;

    @PostConstruct
    public void  init(){
        Connection connection = null;
        ResultSet tables = null;
        try {
            connection = hikariDataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            tables = metaData.getTables(connection.getCatalog(), connection.getSchema(), null, new String[]{"TABLE"});
            while(tables.next()){
                String table_name = tables.getString("TABLE_NAME");
                ResultSet columns = metaData.getColumns(connection.getCatalog(), connection.getSchema(), table_name, "");
                Set<String> columnSet = new HashSet<>();
                tablesMap.put(table_name,columnSet);
                while(columns.next()){
                    String column_name = columns.getString("COLUMN_NAME");
                    columnSet.add(column_name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(tables!=null){
                try {
                    tables.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void createTable(String sql){
        log.info("创建的表结构sql:"+sql);
        Connection connection = null;
        try {
            connection = hikariDataSource.getConnection();
            int i = connection.createStatement().executeUpdate(sql);
            if(i<0){
                log.info("创建的表结构失败！");
            }else{
                log.info("创建的表结构成功！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}

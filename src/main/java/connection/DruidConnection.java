package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Druid连接池
 * @author tangpengfei
 */
public class DruidConnection {
    
    static DruidDataSource dataSource;
    
    static {
        Properties prop = new Properties();
        try {
            prop.load(DruidConnection.class.getClassLoader().getResourceAsStream("/resources/druid.properties"));
            dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(prop);
            //dataSource.addFilters("stat,log4j,wall");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getRDSConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
			return null;
        }
    }
}
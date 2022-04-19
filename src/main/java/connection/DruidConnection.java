package connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import static connection.MyBatisUtil.MybatisConfResource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * Druid连接池
 * 
 * @author tangpengfei
 */
public class DruidConnection {

	static DruidDataSource dataSource;
//非mybatis連線用
	static final String DRUID_RESOURCE = "druid.properties";

	static {
		Properties prop = new Properties();
		try {
			prop.load(DruidConnection.class.getClassLoader().getResourceAsStream(DRUID_RESOURCE));
			dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(prop);
			if (dataSource != null) {
				System.out.println("DRUID取得DataSource成功");
			}
			// dataSource.addFilters("stat,log4j,wall");
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
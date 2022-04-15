package connection;

import java.io.IOException;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidMybatisDataSourceFactory implements DataSourceFactory {

	private Properties prop;

	@Override
	public DataSource getDataSource() {
		DruidDataSource dataSource = null;
		try {

			prop.load(DruidMybatisDataSourceFactory.class.getClassLoader()
					.getResourceAsStream("/resources/druid.properties"));
			dataSource = (DruidDataSource)DruidDataSourceFactory.createDataSource(prop);
			dataSource.init();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}

	@Override
	public void setProperties(Properties arg0) {
		this.prop = arg0;

	}

}

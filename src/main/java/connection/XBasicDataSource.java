package connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class XBasicDataSource extends BasicDataSource {
	// 請參考 https://blog.actorsfit.com/a?ID=00850-2b3808b2-5193-43e0-b842-e36f140fee94
	
    @Override
    public synchronized void close () throws SQLException {
     //The following two sentences of code respectively correspond to the closing of two resources
        DriverManager.deregisterDriver(DriverManager.getDriver(getUrl()));
        AbandonedConnectionCleanupThread.checkedShutdown();
        super .close();
    }
}
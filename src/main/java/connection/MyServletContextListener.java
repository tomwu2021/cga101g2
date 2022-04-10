package connection;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class MyServletContextListener implements ServletContextListener{

    @Override
    public void contextInitialized (ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed (ServletContextEvent servletContextEvent) {
     //Here if the web application has multiple database connections, they can be closed together
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver = null ;
         while (drivers.hasMoreElements()) {
             try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            } catch (SQLException ex) {
            }
        }
        AbandonedConnectionCleanupThread.checkedShutdown();
    }
}
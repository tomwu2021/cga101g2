package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JNDIConnection {
	
	public static Connection getRDSConnection(){
		Connection con = null;
		try {
			DataSource ds = (DataSource) new javax.naming.InitialContext().lookup("java:comp/env/jdbc/cga_02");
			con = ds.getConnection();
			if (con != null) {
				System.out.println("連線成功!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return con;
	}
}

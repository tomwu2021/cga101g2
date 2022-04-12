package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	public static final String USERNAME = "cga_02";
	public static final String PASSWORD = "cga_02";
	public static final String URL = "jdbc:mysql://database-1.czlvj0ycwyxq.ap-northeast-1.rds.amazonaws.com:3306/cga_02?serverTimezone=Asia/Taipei";
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public static Connection getRDSConnection() {
		Connection con = null;
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			if (con != null) {
				System.out.println("連線成功!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}

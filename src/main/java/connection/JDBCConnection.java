package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	// MySQL 8之後連線URL需加上SSL與時區設定
	public final static String URL = "jdbc:mysql://localhost:3306/bookshop_jdbc?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	// MySQL 8之前
	// public final static String URL = "jdbc:mysql://localhost:3306/bookshop_jdbc";
	
	public final static String USERNAME = "root";
	public final static String PASSWORD = "840614";
	
//	private static final String USERNAME = "cga_02";
//	private static final String PASSWORD = "cga_02";
//	private static final String URL = 
//			"jdbc:mysql://database-1.czlvj0ycwyxq.ap-northeast-1.rds.amazonaws.com:3306/cga_02?serverTimezone=Asia/Taipei";

	public static Connection getRDSConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
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

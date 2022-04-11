package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

	private static final String USERNAME = "cga_02";
	private static final String PASSWORD = "cga_02";
	private static final String URL = "jdbc:mysql://database-1.czlvj0ycwyxq.ap-northeast-1.rds.amazonaws.com:3306/cga_02?serverTimezone=Asia/Taipei";

	public Connection getRDSConnection() {
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

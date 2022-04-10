package connection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
	
	
	public static void main(String[] args) throws SQLException {
		Connection con =new JDBCConnection().getRDSConnection();

	}
	
}

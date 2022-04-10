package com.common.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import connection.JDBCConnection;

public class MappingDAO {

	Connection con;

	public void insertOneMapping(MappingTableDto mtd) {

		con = JDBCConnection.getRDSConnection();

		String sql = "insert into " + mtd.getTableName() + "(" + mtd.getColumn1() + ", " + mtd.getColumn2()
				+ ") values(?,?);";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, mtd.getId1());
				stmt.setInt(2, mtd.getId2());
				stmt.execute();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void insertMultiMapping(List<MappingTableDto> mtds) {
		con = JDBCConnection.getRDSConnection();
		if (con != null) {
			try {
				PreparedStatement stmt = null;
				for (MappingTableDto mtd : mtds) {
					String sql = "insert into " + mtd.getTableName() + "(" + mtd.getColumn1() + ", " + mtd.getColumn2()
							+ ") values(?,?);";
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, mtd.getId1());
					stmt.setInt(2, mtd.getId2());
					stmt.addBatch();
				}
				stmt.executeBatch();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

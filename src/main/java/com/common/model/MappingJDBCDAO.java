package com.common.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.picture.model.PictureVO;

import connection.JDBCConnection;

public class MappingJDBCDAO {

	Connection con;

	public boolean insertOneMapping(MappingTableDto mtd, Connection con) {
		String sql = "insert into " + mtd.getTableName1() + "(" + mtd.getColumn1() + ", " + mtd.getColumn2()
				+ ") values(?,?);";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, mtd.getId1());
				stmt.setInt(2, mtd.getId2());
				stmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean insertMultiMapping(List<MappingTableDto> mtds, Connection con) {
		if (con != null) {
			try {
				PreparedStatement stmt = null;
				for (MappingTableDto mtd : mtds) {
					String sql = "insert into " + mtd.getTableName1() + "(" + mtd.getColumn1() + ", " + mtd.getColumn2()
							+ ") values(?,?);";
					stmt = con.prepareStatement(sql);
					stmt.setInt(1, mtd.getId1());
					stmt.setInt(2, mtd.getId2());
					stmt.addBatch();
				}
				stmt.executeBatch();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean deleteOneMapping(MappingTableDto mtd, Connection con) {
		String sql = "DELETE FROM " + mtd.getTableName1() + "WHERE " + mtd.getColumn1() + "=? AND" + mtd.getColumn2()
				+ "=?";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, mtd.getId1());
				stmt.setInt(2, mtd.getId2());
				stmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean deleteMultiMapping(MappingTableDto mtd, Connection con) {
		String sql = "DELETE FROM " + mtd.getTableName1() + "WHERE " + mtd.getColumn1() + "=?";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, mtd.getId1());
				stmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	
	
}
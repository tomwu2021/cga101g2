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
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean insertOneMapping(MappingTableDto mtd) {
		Connection con = JDBCConnection.getRDSConnection();

		try {
			if (insertOneMapping(mtd, con)) {
				con.close();
				return true;
			} else {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean insertMultiMapping(List<MappingTableDto> mtds, Connection con) {
		if (con != null) {
			try {
				PreparedStatement stmt = null;
				String table1 = mtds.get(0).getTableName1();
				String column1 = mtds.get(0).getColumn1();
				String column2 = mtds.get(0).getColumn2();
				String sql = "insert into " + table1 + "(" + column1 + ", " + column2
				+ ") values(?,?);";
				stmt = con.prepareStatement(sql);
				for (MappingTableDto mtd : mtds) {
					stmt.setInt(1, mtd.getId1());
					stmt.setInt(2, mtd.getId2());
					stmt.addBatch();
				}
				stmt.executeBatch();
				return true;
			} catch (SQLException e) {
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
				stmt.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	public boolean deleteOnePictureMapping(MappingTableDto mtd, Connection con) {
		String sql = "DELETE FROM " + mtd.getTableName1() + " WHERE picture_id = ?";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, mtd.getId1());
				stmt.executeUpdate();
				stmt.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean deleteOnePictureMapping(MappingTableDto mtd) {
		Connection con = JDBCConnection.getRDSConnection();
		deleteOnePictureMapping(mtd, con);
		try {
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteOneMapping(MappingTableDto mtd) {
		Connection con = JDBCConnection.getRDSConnection();
		deleteOneMapping(mtd, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean deleteMultiMapping(MappingTableDto mtd, Connection con) {
		String sql = "DELETE FROM " + mtd.getTableName1() + " WHERE " + mtd.getColumn1() + "=?";
		if (con != null) {
			try {
				PreparedStatement stmt = con.prepareStatement(sql);
				stmt.setInt(1, mtd.getId1());
				stmt.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}
	public boolean deleteMultiMapping(MappingTableDto mtd) throws SQLException {
		Connection con = JDBCConnection.getRDSConnection();
		if(deleteMultiMapping(mtd,con)) {
			con.close();
			return true;
		}
		con.close();
		return false;
		
	}


}

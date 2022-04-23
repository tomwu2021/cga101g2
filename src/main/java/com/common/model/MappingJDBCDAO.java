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
		String sql = "DELETE FROM `" + mtd.getTableName1() + "` WHERE picture_id = ?";
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
		String sql = "DELETE FROM " + mtd.getTableName1() + "WHERE " + mtd.getColumn1() + "=?";
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

}

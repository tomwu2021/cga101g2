package com.sort_max.model;

import static connection.JDBCConnection.username;
import static connection.JDBCConnection.password;
import static connection.JDBCConnection.theURL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Sort_maxJDBCDAO implements Sort_maxDAO_interface {
	
	private static final String INSERT_STMT = 
			"INSERT INTO cga_02.sort_mix(sort1_id,sort2_id) "
			+ "VALUES (?,?);";
		private static final String GET_ALL_STMT = 
			"SELECT sort1_id,sort2_id "
			+ "FROM cga_02.sort_mix "
			+ "order by sort1_id;";
		private static final String DELETE = 
			"DELETE FROM cga_02.sort_mix where sort1_id= ? and sort2_id = ?;";
//		private static final String UPDATE = 
//			"UPDATE sort_mix "
//			+ "sort1_id= ? "
//			+ "where sort2_id = ?;";
		
	@Override
	public void insert(Sort_maxVO sort_maxVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, sort_maxVO.getSort1_id());
			pstmt.setInt(2, sort_maxVO.getSort2_id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("insert successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Sort_maxVO sort_maxVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, sort_maxVO.getSort1_id());
			pstmt.setInt(2, sort_maxVO.getSort2_id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("delete successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public List<Sort_maxVO> getAll() {
		
		List<Sort_maxVO> list = new ArrayList<Sort_maxVO>();
		Sort_maxVO sort_maxVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				sort_maxVO = new Sort_maxVO();
				sort_maxVO.setSort1_id(rs.getInt("sort1_id"));
				sort_maxVO.setSort2_id(rs.getInt("sort2_id"));
				list.add(sort_maxVO); // Store the row in the list
			}


			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("getall successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		Sort_maxJDBCDAO dao = new Sort_maxJDBCDAO();
		
		// 新增
		Sort_maxVO sort_maxVO1= new Sort_maxVO();
		sort_maxVO1.setSort1_id(2);
		sort_maxVO1.setSort2_id(1);
		dao.insert(sort_maxVO1);
		
		//刪除
//		Sort_maxVO sort_maxVO2= new Sort_maxVO();
//		sort_maxVO2.setSort1_id(1);
//		sort_maxVO2.setSort2_id(4);
//		dao.delete(sort_maxVO2);
		
		//查詢所有
		List<Sort_maxVO> list = dao.getAll();
		for (Sort_maxVO aSort_max : list) {
			System.out.print(aSort_max.getSort1_id() + ",");
			System.out.print(aSort_max.getSort2_id());
			System.out.println();
		}
		
	}

}

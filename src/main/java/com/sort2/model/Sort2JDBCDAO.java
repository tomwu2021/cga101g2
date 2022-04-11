package com.sort2.model;

import static connection.JDBCConnection.username;
import static connection.JDBCConnection.password;
import static connection.JDBCConnection.theURL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sort2JDBCDAO implements Sort2DAO_interface{
	
	private static final String INSERT_STMT = 
			"INSERT INTO cga_02.sort2(sort2_name) VALUES ( ? );";
		private static final String GET_ALL_STMT = 
			"SELECT sort2_id,sort2_name "
			+ "FROM cga_02.sort2 "
			+ "order by sort2_id;";
//		private static final String DELETE = 
//			"DELETE FROM emp2 where empno = ?";
		private static final String UPDATE = 
			"UPDATE cga_02.sort2 "
			+ "SET sort2_name= ? "
			+ "where sort2_id = ?;";
		
	@Override
	public void insert(Sort2VO sort2VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sort2VO.getSort2_name());
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

			// Handle any driver errors
		} catch (SQLException se) {
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
	public void update(Sort2VO sort2VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sort2VO.getSort2_name());
			pstmt.setInt(2, sort2VO.getSort2_id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (con != null) {
				try {
					con.close();
					System.out.println("update successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public List<Sort2VO> getAll() {
		List<Sort2VO> list = new ArrayList<Sort2VO>();
		Sort2VO sort2VO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sort2VO = new Sort2VO();
				sort2VO.setSort2_id(rs.getInt("sort2_id"));
				sort2VO.setSort2_name(rs.getString("sort2_name"));
				list.add(sort2VO); // Store the row in the list
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
		
	}
	
	public static void main(String[] args) {
		
		Sort2JDBCDAO dao = new Sort2JDBCDAO();
		
		//新增
		Sort2VO sort2VO1= new Sort2VO();
		sort2VO1.setSort2_name("433");
		dao.insert(sort2VO1);
		
		//修改
//		Sort2VO sort2VO2= new Sort2VO();
//		sort2VO2.setSort2_name("SSS");
//		sort2VO2.setSort2_id(3);
//		dao.update(sort2VO2);
		
		//查詢全部
		List<Sort2VO> list = dao.getAll();
		for (Sort2VO aSort2 : list) {
			System.out.print(aSort2.getSort2_id() + ",");
			System.out.print(aSort2.getSort2_name());
			System.out.println();
		}
	}
}


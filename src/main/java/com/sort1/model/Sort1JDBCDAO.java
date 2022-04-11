package com.sort1.model;

import static connection.JDBCConnection.username;
import static connection.JDBCConnection.password;
import static connection.JDBCConnection.theURL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Sort1JDBCDAO implements Sort1DAO_interface{
	
	private static final String INSERT_STMT = 
			"INSERT INTO cga_02.sort1(sort1_name) VALUES ( ? );";
		private static final String GET_ALL_STMT = 
			"SELECT sort1_id,sort1_name "
			+ "FROM cga_02.sort1 "
			+ "order by sort1_id;";
//		private static final String DELETE = 
//			"DELETE FROM emp2 where empno = ?";
		private static final String UPDATE = 
			"UPDATE cga_02.sort1 "
			+ "SET sort1_name= ? "
			+ "where sort1_id = ?;";
		
	@Override
	public void insert(Sort1VO sort1VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, sort1VO.getSort1_name());

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
	public void update(Sort1VO sort1VO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, sort1VO.getSort1_name());
			pstmt.setInt(2, sort1VO.getSort1_id());

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
	public List<Sort1VO> getAll() {
		
		List<Sort1VO> list = new ArrayList<Sort1VO>();
		Sort1VO sort1VO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				sort1VO = new Sort1VO();
				sort1VO.setSort1_id(rs.getInt("sort1_id"));
				sort1VO.setSort1_name(rs.getString("sort1_name"));
				list.add(sort1VO); // Store the row in the list
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
		
		Sort1JDBCDAO dao = new Sort1JDBCDAO();
		
		// 新增
		Sort1VO sort1VO1= new Sort1VO();
		sort1VO1.setSort1_name("SALA");
		dao.insert(sort1VO1);
		
		//更改
		Sort1VO sort1VO2= new Sort1VO();
		sort1VO2.setSort1_name("333");
		sort1VO2.setSort1_id(2);
		dao.update(sort1VO2);
		
		//查詢全部
//		List<Sort1VO> list = dao.getAll();
//		for (Sort1VO aSort1 : list) {
//			System.out.print(aSort1.getSort1_id() + ",");
//			System.out.print(aSort1.getSort1_name());
//			System.out.println();
//		}
	}
}


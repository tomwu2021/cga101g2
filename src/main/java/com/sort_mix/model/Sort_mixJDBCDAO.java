package com.sort_mix.model;

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


public class Sort_mixJDBCDAO implements Sort_mixDAO_interface {
	
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
	public void insert(Sort_mixVO sort_maxVO) {
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
	public void delete(Sort_mixVO sort_maxVO) {
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
	public List<Sort_mixVO> getAll() {
		
		List<Sort_mixVO> list = new ArrayList<Sort_mixVO>();
		Sort_mixVO sort_maxVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				sort_maxVO = new Sort_mixVO();
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
		
		Sort_mixJDBCDAO dao = new Sort_mixJDBCDAO();
		
		// �s�W
		Sort_mixVO sort_maxVO1= new Sort_mixVO();
		sort_maxVO1.setSort1_id(2);
		sort_maxVO1.setSort2_id(1);
		dao.insert(sort_maxVO1);
		
		//�R��
//		Sort_maxVO sort_maxVO2= new Sort_maxVO();
//		sort_maxVO2.setSort1_id(1);
//		sort_maxVO2.setSort2_id(4);
//		dao.delete(sort_maxVO2);
		
		//�d�ߩҦ�
		List<Sort_mixVO> list = dao.getAll();
		for (Sort_mixVO aSort_max : list) {
			System.out.print(aSort_max.getSort1_id() + ",");
			System.out.print(aSort_max.getSort2_id());
			System.out.println();
		}
		
	}

}

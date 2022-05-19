package com.sort1.model;

import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.product.model.ProductVO;

import connection.JNDIConnection;

public class Sort1JNDIDAO implements Sort1DAO_interface {

	@Override
	public Sort1VO insert(Sort1VO sort1VO) {

		final String INSERT_STMT = "INSERT INTO cga_02.sort1(sort1_name) VALUES ( ? );";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setString(1, sort1VO.getSort1Name());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort1VO;
	}

	@Override
	public boolean delete(Sort1VO sort1VO) {
		final String DELETE = "DELETE FROM cga_02.sort1 " + "WHERE sort1_id = ?; ";
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE)) {

			pstmt.setInt(1, sort1VO.getSort1Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) delete!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Sort1VO update(Sort1VO sort1VO) {
		final String UPDATE = "UPDATE cga_02.sort1 " + "SET sort1_name= ? " + "where sort1_id = ?;";

		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE)) {

			pstmt.setString(1, sort1VO.getSort1Name());
			pstmt.setInt(2, sort1VO.getSort1Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort1VO;
	}

	@Override
	public Sort1VO getOneById(Integer id) {
		Sort1VO sort1VO = new Sort1VO();

		final String sql = "select * from sort1 where sort1_id = ? ";
		try (Connection conn = getRDSConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					sort1VO.setSort1Id(rs.getInt("sort1_id"));
					sort1VO.setSort1Name(rs.getString("sort1_Name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort1VO;
	}

	@Override
	public Sort1VO selectBySort1Name(String sort1Name) {
		Sort1VO sort1VO = new Sort1VO();
		final String CHECKNAME = "SELECT * from sort1 WHERE sort1_name =? ;";
		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(CHECKNAME)) {
			pstmt.setString(1, sort1Name);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sort1VO.setSort1Name(rs.getString("sort1_name"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort1VO;
	}


	@Override
	public List<Sort1VO> getAll() {
		List<Sort1VO> list = new ArrayList<Sort1VO>();
		final String GET_ALL_STMT = "SELECT sort1_id,sort1_name " + "FROM cga_02.sort1 " + "order by sort1_id;";
		Sort1VO sort1VO = null;

		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				// 回傳sortMixVO 並且 加入 直到沒有下一筆
				sort1VO = new Sort1VO();
				sort1VO.setSort1Id(rs.getInt("sort1_id"));
				sort1VO.setSort1Name(rs.getString("sort1_Name"));
				list.add(sort1VO); // Store the row in the list
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public int getSort1Count() {
		int count = 0;
		final String sql = "SELECT sort1_id FROM sort1;";
		try (Connection conn = getRDSConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					++count;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}

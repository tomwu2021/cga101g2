package com.sort_mix.model;

import static connection.JDBCConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SortMixJDBCDAO implements SortMixDAO_interface {

	
//		private static final String UPDATE = 
//			"UPDATE sort_mix "
//			+ "sort1_id= ? "
//			+ "where sort2_id = ?;";

	@Override
	public SortMixVO insert(SortMixVO sortMixVO) {
		final String INSERT_STMT = "INSERT INTO cga_02.sort_mix(sort1_id,sort2_id) " + "VALUES (?,?);";
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sortMixVO.getSort1Id());
			pstmt.setInt(2, sortMixVO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
			return sortMixVO;

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(SortMixVO sortMixVO) {
	final String DELETE = "DELETE FROM cga_02.sort_mix where sort1_id= ? and sort2_id = ?;";
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(DELETE)) {

			pstmt.setInt(1, sortMixVO.getSort1Id());
			pstmt.setInt(2, sortMixVO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

			return true;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public SortMixVO update(SortMixVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortMixVO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SortMixVO> getAll() {
		final String GET_ALL_STMT = 
				"SELECT sort1_id,sort2_id " 
				+ "FROM cga_02.sort_mix "
				+ "order by sort1_id;";
		
		SortMixVO sortMixVO = null;
		List<SortMixVO> list = new ArrayList<SortMixVO>();

		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT)) {
					ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 回傳sortMixVO 並且 加入 直到沒有下一筆
				sortMixVO = new SortMixVO();
				sortMixVO.setSort1Id(rs.getInt("sort1_id"));
				sortMixVO.setSort2Id(rs.getInt("sort2_id"));
				list.add(sortMixVO); // Store the row in the list
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}

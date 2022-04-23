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
	public int insert(SortMixMapVO sortMixMapVO) {
		final String INSERT_STMT = "INSERT INTO sort_mix VALUES(?,?);";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sortMixMapVO.getSort1Id());
			pstmt.setInt(2, sortMixMapVO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
			return rowCount;

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(SortMixMapVO sortMixMapVO) {
		final String INSERT_STMT = "DELETE FROM sort_mix VALUES(?,?);";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sortMixMapVO.getSort1Id());
			pstmt.setInt(2, sortMixMapVO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
			return 1;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}


	@Override
	public int update(SortMixMapVO sortMixMapVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SortMixMapVO> findAll() {
		final String GET_ALL_STMT = 
				" SELECT  sort1.sort1_id, sort1.sort1_name, sort2.sort2_id, sort2.sort2_name "
				+ "FROM  sort_mix "
				+ "Inner join sort1 on sort1.sort1_id = sort_mix.sort1_id "
				+ "Inner join sort2 on sort2.sort2_id = sort_mix.sort2_id "
				+ "order by sort_mix.sort1_id , sort_mix.sort2_id ; ";
		List<SortMixMapVO> list = new ArrayList<SortMixMapVO>();
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 回傳sortMixVO 並且 加入 直到沒有下一筆
				SortMixMapVO mtd = new SortMixMapVO();
				mtd.setSort1Id(rs.getInt("sort1_id"));
				mtd.setSort1Name(rs.getString("sort1_name"));
				mtd.setSort2Id(rs.getInt("sort2_id"));
				mtd.setSort2Name(rs.getString("sort2_name"));
				list.add(mtd); // Store the row in the list
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SortMixMapVO> getBySet() {
//		final String GET_ALL_STMT = 
//				" SELECT  sort1.sort1_id, sort1.sort1_name, sort2.sort2_id, sort2.sort2_name "
//				+ "FROM  sort_mix "
//				+ "Inner join sort1 on sort1.sort1_id = sort_mix.sort1_id "
//				+ "Inner join sort2 on sort2.sort2_id = sort_mix.sort2_id "
//				+ "WHERE sort1.sort1_id = ? "
//				+ "order by sort_mix.sort1_id , sort_mix.sort2_id ; ";
//		List<MappingTableDto> list = new ArrayList<MappingTableDto>();
//		try (Connection connection = getRDSConnection();
//				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT)) {
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				// 回傳sortMixVO 並且 加入 直到沒有下一筆
//				MappingTableDto mtd = new MappingTableDto();
//				mtd.setId1(rs.getInt("sort1_id"));
//				mtd.setColumn1(rs.getString("sort1_name"));
//				mtd.setId2(rs.getInt("sort2_id"));
//				mtd.setColumn2(rs.getString("sort2_name"));
//				list.add(mtd); // Store the row in the list
//			}
//	
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}


}

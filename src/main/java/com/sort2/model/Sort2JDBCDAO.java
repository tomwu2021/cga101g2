package com.sort2.model;

import static connection.JDBCConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sort1.model.Sort1VO;

public class Sort2JDBCDAO implements Sort2DAO_interface {

//		private static final String DELETE = 
//			"DELETE FROM emp2 where empno = ?";
//	private static final String UPDATE = "UPDATE cga_02.sort2 " + "SET sort2_name= ? " + "where sort2_id = ?;";

	@Override
	public Sort2VO insert(Sort2VO sort2VO) {
		final String INSERT_STMT = "INSERT INTO cga_02.sort2(sort2_name) VALUES ( ? );";
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS)) {

			pstmt.setString(1, sort2VO.getSort2Name());

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				sort2VO.setSort2Id(rs.getInt(1));
			}

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort2VO;
	}

	@Override
	public boolean delete(Sort2VO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Sort2VO update(Sort2VO sort2VO) {

		final StringBuilder UPDATE = new StringBuilder().append("UPDATE cga_02.sort2 SET ");

		final String sort2Name = sort2VO.getSort2Name();
		if (sort2Name != null && !sort2Name.isEmpty()) {
			// "UPDATE members SET password = ?, phone = ?, "
			UPDATE.append(" sort2_name = ? ");
		}
		UPDATE.append(" where sort2_id = ? ;");

		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE.toString())) {
			int offset = 1;
			pstmt.setString(offset, sort2VO.getSort2Name());
			offset += 1;
			pstmt.setInt(2, sort2VO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort2VO;
	}

	@Override
	public Sort2VO getOneById(Integer sort2Id) {
		Sort2VO sort2VO = new Sort2VO();
		final String sql = "select * from sort2 where sort2_id = ?";
		try (Connection conn =  getRDSConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, sort2Id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					sort2VO.setSort2Id(rs.getInt("sort2_id"));
					sort2VO.setSort2Name(rs.getString("sort2_Name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort2VO;
	}

	@Override
	public Sort2VO selectBySort2Name(String sort2Name) {
		Sort2VO sort2VO = new Sort2VO();
		final String sql = "select * from sort2 where sort2_name = ?";
		try (Connection conn =  getRDSConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, sort2Name);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					sort2VO.setSort2Id(rs.getInt("sort2_id"));
					sort2VO.setSort2Name(rs.getString("sort2_Name"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort2VO;
	}

	@Override
	public List<Sort2VO> getAll() {
		List<Sort2VO> list = new ArrayList<Sort2VO>();
		final String GET_ALL_STMT = "SELECT sort2_id,sort2_name " + "FROM cga_02.sort2 " + "order by sort2_id;";
	
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
	
			while (rs.next()) {
				// 回傳sortMixVO 並且 加入 直到沒有下一筆
				Sort2VO sort2VO = new Sort2VO();
				sort2VO.setSort2Id(rs.getInt("sort2_id"));
				sort2VO.setSort2Name(rs.getString("sort2_Name"));
				list.add(sort2VO); // Store the row in the list
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Sort1VO> sort1VOList() {
		// TODO Auto-generated method stub
		return null;
	}
}

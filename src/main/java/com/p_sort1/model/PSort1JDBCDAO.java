package com.p_sort1.model;

import static connection.JDBCConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sort1.model.Sort1VO;

public class PSort1JDBCDAO implements PSort1DAO_interface{

	@Override
	public PSort1VO insert(PSort1VO pSort1VO) {
		String INSERT_STMT = "INSERT INTO cga_02.p_sort1(product_id,sort1_id) VALUES (?,?) ; ";
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
	
			pstmt.setInt(1, pSort1VO.getProductId());
			pstmt.setInt(2, pSort1VO.getSort1Id());
	
			int rowCount = pstmt.executeUpdate();
			System.out.println("PSort1VO" +rowCount + "row(s) insert!");
			return pSort1VO;
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(PSort1VO t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PSort1VO update(PSort1VO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PSort1VO getOneById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PSort1VO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sort1VO> findSort1VOByproductId(Integer productId) {
		List<Sort1VO> sort1VOList = new ArrayList<Sort1VO>();
		
		String FIND_STMT= "SELECT * "
				+ "FROM sort1 s1, p_sort1 ps1 "
				+ "WHERE s1.sort1_id = ps1.sort1_id "
				+ "AND   ps1.product_id = ? ; ";
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_STMT)) {

			pstmt.setInt(1, productId);
			
			ResultSet rsSet = pstmt.executeQuery();
			while (rsSet.next()) {
				//Sort1子分類訊息
				Sort1VO sort1VO = new Sort1VO();
				sort1VO.setSort1Id(rsSet.getInt("sort1_id"));
				sort1VO.setSort1Name(rsSet.getString("sort1_name"));
				sort1VOList.add(sort1VO);
			}
			System.out.println("Sort2VO findAllBySort2Id(Integer sort2Id)成功執行");
			return sort1VOList;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}


}

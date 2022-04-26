package com.sort_mix.model;

import static connection.JDBCConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;

public class SortMixJDBCDAO implements SortMixDAO_interface {

	
	//查詢某個主分類(包含對應的子分類)
	@Override
	public Sort1VO findAllBySort1Id(Integer sort1Id) {
		// 創建主分類實體跟子分類"集合"實體
		Sort1VO sort1VO = new Sort1VO();
		List<Sort2VO> sort2VOList = new ArrayList<Sort2VO>();
		
		String FIND_STMT= "SELECT * "
				+ "FROM sort1 s1, sort2 s2, sort_mix sm "
				+ "WHERE s1.sort1_id = sm.sort1_id "
				+ "AND   s2.sort2_id = sm.sort2_id "
				+ "AND   s1.sort1_id=? ";
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_STMT)) {

			pstmt.setInt(1, sort1Id);
			
			ResultSet rsSet = pstmt.executeQuery();
			//此時結果集合包含兩張表格的數據,先分別獲取各自表中的數據
			while (rsSet.next()) {
				//Sort1主分類訊息
				sort1VO.setSort1Id(rsSet.getInt("sort1_id"));
				sort1VO.setSort1Name(rsSet.getString("sort1_name"));
				//Sort2子分類訊息
				Sort2VO sort2VO = new Sort2VO();
				sort2VO.setSort2Id(rsSet.getInt("sort2_id"));
				sort2VO.setSort2Name(rsSet.getString("sort2_name"));
				//建立兩張表格的關係
				//把子分類"集合"放入主分類 即 主分類為一個物件,這個物件裡面有多個集合
				sort2VOList.add(sort2VO);
			}
			
			sort1VO.setSort2VOList(sort2VOList);
			return sort1VO;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return null;
	}

	//查詢某個子分類(包含對應的主分類) 給商品使用對應
	@Override
	public Sort2VO findAllBySort2Id(Integer sort2Id) {
		Sort2VO sort2VO = new Sort2VO();
		List<Sort1VO> sort1VOList = new ArrayList<Sort1VO>();
		
		String FIND_STMT= "SELECT * "
				+ "FROM sort1 s1, sort2 s2, sort_mix sm "
				+ "WHERE s1.sort1_id = sm.sort1_id "
				+ "AND   s2.sort2_id = sm.sort2_id "
				+ "AND   s2.sort2_id=? ";
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_STMT)) {

			pstmt.setInt(1, sort2Id);
			
			ResultSet rsSet = pstmt.executeQuery();
			//此時結果集合包含兩張表格的數據,先分別獲取各自表中的數據
			while (rsSet.next()) {
				//Sort2子分類訊息
				sort2VO.setSort2Id(rsSet.getInt("sort2_id"));
				sort2VO.setSort2Name(rsSet.getString("sort2_name"));
				//Sort2子分類訊息
				Sort1VO sort1VO = new Sort1VO();
				sort1VO.setSort1Id(rsSet.getInt("sort1_id"));
				sort1VO.setSort1Name(rsSet.getString("sort1_name"));
				//建立兩張表格的關係
				//把主分類"集合"放入子分類 即 主分類為一個物件,這個物件裡面有多個集合
				sort1VOList.add(sort1VO);
			}
			
			sort2VO.setSort1VOList(sort1VOList);
			return sort2VO;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

	@Override
	public SortMixVO insert(SortMixVO sortMixVO) {
		final String INSERT_STMT = "INSERT INTO sort_mix VALUES(?,?);";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

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
		final String INSERT_STMT = "DELETE FROM sort_mix VALUES(?,?);";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sortMixVO.getSort1Id());
			pstmt.setInt(2, sortMixVO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
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
				" SELECT  sort1.sort1_id, sort1.sort1_name, sort2.sort2_id, sort2.sort2_name "
				+ "FROM  sort_mix "
				+ "Inner join sort1 on sort1.sort1_id = sort_mix.sort1_id "
				+ "Inner join sort2 on sort2.sort2_id = sort_mix.sort2_id "
				+ "order by sort_mix.sort1_id , sort_mix.sort2_id ; ";
		List<SortMixVO> list = new ArrayList<SortMixVO>();
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				// 回傳sortMixVO 並且 加入 直到沒有下一筆
				SortMixVO mtd = new SortMixVO();
				mtd.setSort1Id(rs.getInt("sort1_id"));
				mtd.setSort1Name(rs.getString("sort1_name"));
				mtd.setSort2Id(rs.getInt("sort2_id"));
				mtd.setSort2Name(rs.getString("sort2_name"));
				list.add(mtd); // Store the row in the list
			}
			return list;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}




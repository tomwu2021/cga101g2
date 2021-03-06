package com.sort_mix.model;

import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.product.model.ProductVO;
import com.product.model.jdbcUtil_CompositeQuery_ProductForFront;
import com.sort1.model.Sort1VO;
import com.sort2.model.Sort2VO;

public class SortMixJNIDIDAO implements SortMixDAO_interface {

	
	// 查詢某個主分類(包含對應的子分類)
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
				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. " + se.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				} 
				return sort1VO;
	}

	// 查詢某個子分類(包含對應的主分類) 給商品使用對應
	@Override
	public Sort2VO findSort2VOSort1VOsBySort2Id(Integer sort2Id) {
		Sort2VO sort2VO = new Sort2VO();
		List<Sort1VO> sort1VOList = new ArrayList<Sort1VO>();
		
		String FIND_STMT= "SELECT * "
				+ "FROM sort1 s1, sort2 s2, sort_mix sm "
				+ "WHERE s1.sort1_id = sm.sort1_id "
				+ "AND   s2.sort2_id = sm.sort2_id "
				+ "AND   s2.sort2_id=? "
				+ "ORDER BY  s2.sort2_id ";
		
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
			System.out.println("public Sort2VO findSort2VOSort1VOsBySort2Id(Integer sort2Id)成功執行");
			sort2VO.setSort1VOList(sort1VOList);
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort2VO;
	}

	@Override
	public SortMixVO insert(SortMixVO sortMixVO) {
		final String INSERT_STMT = "INSERT INTO sort_mix VALUES(?,?);";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sortMixVO.getSort1Id());
			pstmt.setInt(2, sortMixVO.getSort2Id());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "SortMixVOSortMixVOSortMixVOrow(s) SortMixVOSortMixVOSortMixVOSortMixVOinsert!");

		} catch (SQLException se) {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMixVO;
	}

	@Override
	public boolean delete(SortMixVO sortMixVO) {
		final String INSERT_STMT = "DELETE FROM sort_mix WHERE sort1_id = ?  AND sort2_id = ? ;";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sortMixVO.getSort1Id());
			pstmt.setInt(2, sortMixVO.getSort2Id());

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
	public boolean deleteBySort2Id(Integer sort2Id) {
		final String INSERT_STMT = "DELETE FROM sort_mix WHERE sort2_id = ? ";
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sort2Id);

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
	public boolean deleteBySort1Id(Integer sort1Id) {
		final String INSERT_STMT = "DELETE FROM sort_mix WHERE sort1_id = ? ";
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, sort1Id);

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
	public SortMixVO update(SortMixVO t) {
		return null;
	}

	@Override
	public SortMixVO getOneById(Integer id) {
		return null;
	}

	@Override
	public List<SortMixVO> getAll() {
		List<SortMixVO> list = new ArrayList<SortMixVO>();
		final String GET_ALL_STMT = 
				" SELECT  sort1.sort1_id, sort1.sort1_name, sort2.sort2_id, sort2.sort2_name "
				+ "FROM  sort_mix "
				+ "Inner join sort1 on sort1.sort1_id = sort_mix.sort1_id "
				+ "Inner join sort2 on sort2.sort2_id = sort_mix.sort2_id "
				+ "order by sort_mix.sort1_id , sort_mix.sort2_id ; ";
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
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Sort1VO> getSort1VOsBySort2Id(Integer sort2Id) {
		List<Sort1VO> sort1VOList = new ArrayList<Sort1VO>();
		String FIND_STMT= "SELECT * "
				+ "FROM sort1 s1, sort2 s2, sort_mix sm "
				+ "WHERE s1.sort1_id = sm.sort1_id "
				+ "AND   s2.sort2_id = sm.sort2_id "
				+ "AND   s2.sort2_id=? ";
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_STMT)) {
			pstmt.setInt(1, sort2Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Sort1VO sort1VO = new Sort1VO();
				sort1VO.setSort1Id(rs.getInt("sort1_id"));
				sort1VO.setSort1Name(rs.getString("sort1_name"));
				sort1VOList.add(sort1VO);
		}	
			System.out.println("List<Sort1VO> findSort1VOBySort2Id(Integer sort2Id)成功執行");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort1VOList;
	}

	@Override
	public List<Sort2VO> getSort2VOListBySort1Id(Integer sort1Id) {
		List<Sort2VO> sort2VOList = new ArrayList<Sort2VO>();
		String FIND_STMT= "SELECT * "
				+ "FROM sort1 s1, sort2 s2, sort_mix sm "
				+ "WHERE s1.sort1_id = sm.sort1_id "
				+ "AND   s2.sort2_id = sm.sort2_id "
				+ "AND   s1.sort1_id=? ";
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_STMT)) {
			pstmt.setInt(1, sort1Id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Sort2VO sort2VO = new Sort2VO();
				sort2VO.setSort2Id(rs.getInt("sort2_id"));
				sort2VO.setSort2Name(rs.getString("sort2_name"));
				sort2VOList.add(sort2VO);
		}	
			System.out.println("List<Sort1VO> findSort1VOBySort2Id(Integer sort2Id)成功執行");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sort2VOList;
	}

	@Override
	public List<ProductVO> getProductIdByMap(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getRDSConnection();
			String finalSQL = "select product.product_id "
					+ "FROM  product "
					+ "JOIN p_sort1 "
					+" ON product.product_id = p_sort1.product_id "	
			        + "WHERE product.status  NOT IN (999)"
			        + jdbcUtil_CompositeQuery_ProductForFront.get_WhereConditionProductForFront(map)
			        + "LIMIT 1 ";
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("List<ProductVO> getProductIdBySortMixVO(Map<String, String[]> map):" + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				list.add(productVO); 
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
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

	@Override
	public SortMixVO getOneBySortMixVO(SortMixVO sortMixVO) {
		SortMixVO sMixVO = new SortMixVO();
		
		String FIND_STMT= "SELECT * "
				+ "FROM  sort_mix "
				+ "WHERE sort1_id = ? "
				+ "AND   sort2_id = ? ;";
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(FIND_STMT)) {

			pstmt.setInt(1, sortMixVO.getSort1Id());
			pstmt.setInt(2, sortMixVO.getSort2Id());
			
			ResultSet rs = pstmt.executeQuery();
			//此時結果集合包含兩張表格的數據,先分別獲取各自表中的數據
			while (rs.next()) {
				sMixVO.setSort1Id(rs.getInt("sort1_id"));
				sMixVO.setSort2Id(rs.getInt("sort2_id"));
			}
			System.out.println(sMixVO.getSort1Id());
			System.out.println(sMixVO.getSort2Id());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return sMixVO;
	}
	
	
	public static void main(String[] args) {
		SortMixJNIDIDAO DAO = new SortMixJNIDIDAO();
		SortMixVO sMixVO = new SortMixVO();
		sMixVO.setSort1Id(6);
		sMixVO.setSort2Id(1);
		sMixVO = DAO.getOneBySortMixVO(sMixVO);
		System.out.println("==========sort1Id "+sMixVO.getSort1Id());
		System.out.println("==========sort2Id "+sMixVO.getSort2Id());
	}
}

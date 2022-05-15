package com.product.model;

import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.product_img.model.ProductImgVO;
import com.sort2.model.Sort2VO;

public class ProductJNDIDAO implements ProductDAO_interface {

	
	private static final String INSERT_STMT = "INSERT INTO cga_02.product(product_name,price,amount, "
			+ "group_amount1,group_amount2,group_amount3,group_price1, " + "sort2_id,description,status,top_status) "
			+ "VALUES (?, ? , ?, ? , ? , ? , ? , ? , ? , 0 , 0 ) ; ";
	private static final String GET_ALL_STMT = "SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status "
			+ "FROM cga_02.product ORDER BY  product_id DESC ; ";
	private static final String GET_ONE_STMT = "SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status " + "FROM cga_02.product " + "WHERE product_id = ? ; ";
	private static final String DELETE = "UPDATE  cga_02.product " + "SET status = ? " + "WHERE product_id = ? ;";

	private static final String DELETEBYTOP_STATUS = "UPDATE  cga_02.product " + "SET top_status = ? "
			+ "WHERE product_id = ? ;";

	private static final String GET_PIMGs_ByPId_STMT = "SELECT product_img_id,product_id,product_img_url,file_key,file_name,preview_url"
			+ "	FROM product_img " + "	where product_id = ? " + "	order by product_id ; ";

	@Override
	public ProductVO insert(ProductVO productVO) {
		
		// 設定自動新增的主鍵名稱
				String columns[] = { "product_id" };
				try (Connection con = getRDSConnection();
						PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, columns)) {

					pstmt.setString(1, productVO.getProductName());
					pstmt.setInt(2, productVO.getPrice());
					pstmt.setInt(3, productVO.getAmount());
					pstmt.setInt(4, productVO.getGroupAmount1());
					pstmt.setInt(5, productVO.getGroupAmount2());
					pstmt.setInt(6, productVO.getGroupAmount3());
					pstmt.setInt(7, productVO.getGroupPrice1());
					pstmt.setInt(8, productVO.getSort2Id());
					pstmt.setString(9, productVO.getDescription());

					int rowCount = pstmt.executeUpdate();
					System.out.println("ProductVO" + rowCount + "row(s) insert!");

					// 掘取對應的自增主鍵值
					int next_product_id = 0;
					ResultSet rs = pstmt.getGeneratedKeys();// 取得自動編號
					if (rs.next()) {
						next_product_id = rs.getInt(1);// 與上述無關,單純取得自動編號
						System.out.println("自增主鍵值= " + next_product_id + "(剛新增成功的產品編號)");
					} else {
						System.out.println("未取得自增主鍵值");
					}
					// 把取得的主鍵放入productVO,讓servlet可以get
					productVO.setProductId(next_product_id);

				} catch (SQLException se) {
					throw new RuntimeException("A database error occured. " + se.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				return productVO;
	}

	@Override
	public ProductVO update(ProductVO productVO) {
		System.out.println("進入JNDI ProductVO update 的範圍");
		final StringBuilder UPDATE = new StringBuilder().append("UPDATE cga_02.product SET ");
		final String productName = productVO.getProductName();
		if (productName != null && !productName.trim().isEmpty()) {
			UPDATE.append("product_name = ?, ");
		}
		final int price = productVO.getPrice();
		if (price > 0) {
			UPDATE.append("price = ?, ");
		}
		final int amount = productVO.getAmount();
		if (amount > 0) {
			UPDATE.append("amount = ?, ");
		}
		final int groupAmount1 = productVO.getGroupAmount1();
		if (groupAmount1 > 0) {
			UPDATE.append("group_amount1 = ?, ");
		}
		final int groupAmount2 = productVO.getGroupAmount2();
		if (groupAmount2 > 0) {
			UPDATE.append("group_amount2 = ?, ");
		}
		final int groupAmount3 = productVO.getGroupAmount3();
		if (groupAmount3 > 0) {
			UPDATE.append("group_amount3 = ?, ");
		}
		final int groupPrice1 = productVO.getGroupPrice1();
		if (groupPrice1 > 0) {
			UPDATE.append("group_price1 = ?, ");
		}
		UPDATE.append("sort2_id = ?, ");
		final String description = productVO.getDescription();
		if (description != null && !description.trim().isEmpty()) {
			UPDATE.append("description = ? ,");
		}
		UPDATE.append("update_time = NOW() ");
		UPDATE.append("WHERE product_id = ? ; ");

		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(UPDATE.toString())) {

			if (productName != null && !productName.isEmpty()) {
				pstmt.setString(1, productVO.getProductName());
			}
			if (price > 0) {
				pstmt.setInt(2, productVO.getPrice());
			}
			if (amount > 0) {
				pstmt.setInt(3, productVO.getAmount());
			}
			if (groupAmount1 > 0) {
				pstmt.setInt(4, productVO.getGroupAmount1());
			}
			if (groupAmount2 > 0) {
				pstmt.setInt(5, productVO.getGroupAmount2());
			}
			if (groupAmount3 > 0) {
				pstmt.setInt(6, productVO.getGroupAmount3());
			}
			if (groupPrice1 > 0) {
				pstmt.setInt(7, productVO.getGroupPrice1());
			}
			pstmt.setInt(8, productVO.getSort2Id());
			if (description != null && !description.isEmpty()) {
				pstmt.setString(9, productVO.getDescription());
			}
			pstmt.setInt(10, productVO.getProductId());

			pstmt.executeUpdate();

			System.out.println("送出新增的SQL字串"+UPDATE);
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) update!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productVO;


	}

	@Override
	public boolean delete(ProductVO productVO) {
		
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			pstmt.setInt(1, productVO.getStatus());
			pstmt.setInt(2, productVO.getProductId());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteByTopStatus(Integer topStatus,Integer productId) {
		
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(DELETEBYTOP_STATUS)) {

			pstmt.setInt(1, topStatus);
			pstmt.setInt(2, productId);

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) update(delete)!");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public ProductVO getOneById(Integer id) {
		ProductVO productVO = new ProductVO();
		
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAmount(rs.getInt("amount"));
				productVO.setUpdateTime(rs.getTimestamp("update_time"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setSort2Id(rs.getInt("sort2_id"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setTopStatus(rs.getInt("top_status"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productVO;
	}

	@Override
	public Set<ProductImgVO> getPImgVOsByPdID(Integer pdID) {
		Set<ProductImgVO> set = new LinkedHashSet<ProductImgVO>();
		
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_PIMGs_ByPId_STMT)) {
			pstmt.setInt(1, pdID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductImgVO pImgVO = new ProductImgVO();
				pImgVO.setProductImgId(rs.getInt("product_img_id"));
				pImgVO.setProductId(rs.getInt("product_id"));
				set.add(pImgVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return set;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		System.out.println("List<ProductVO> getAll() 執行成功");
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductName(rs.getString("product_name"));
//				productVO.setPrice(rs.getInt("price"));
//				productVO.setAmount(rs.getInt("amount"));
//				productVO.setUpdateTime(rs.getTimestamp("update_time"));
//				productVO.setGroupAmount1(rs.getInt("group_amount1"));
//				productVO.setGroupAmount2(rs.getInt("group_amount2"));
//				productVO.setGroupAmount3(rs.getInt("group_amount3"));
//				productVO.setGroupPrice1(rs.getInt("group_price1"));
//				productVO.setSort2Id(rs.getInt("sort2_id"));
//				productVO.setDescription(rs.getString("description"));
//				productVO.setStatus(rs.getInt("status"));
//				productVO.setTopStatus(rs.getInt("top_status"));
				list.add(productVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ProductVO> getAll(Map<String, String[]> map, int pageSize, int pageNo) {
		System.out.println("進入後臺查詢用的dao");
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		for(String key : map.keySet()){
			   String[] value = map.get(key);
			   System.out.println(key+"  "+value);
			}
		
		//*****Sort1分界點*****//
		//有輸入sort1Id才串 ,sort1Id有可能是空陣列
		String[] sort1Id = null;
		StringBuffer andSort1Id = new StringBuffer();
		
		if((String[])map.get("sort1_id") != null) {
			sort1Id = (String[])map.get("sort1_id");
			
			andSort1Id.append(" AND p_sort1.sort1_id ");
			int count = 0;
			
			if(sort1Id!=null) {
				for (int i = 0; i < sort1Id.length; i++) {
					System.out.println("分類串接");
					System.out.println("從listAllproduct.jsp獲得查詢sort1["+i+"],sort1id : " + sort1Id[i]);
					count++;
					if (count == 1) {
						andSort1Id.append(" in ");
						andSort1Id.append(" ( ");
						andSort1Id.append(Integer.valueOf(sort1Id[i]));
					}
					if (count > 1) {
						andSort1Id.append(",");
						andSort1Id.append(Integer.valueOf(sort1Id[i]));
					}
					if (count == sort1Id.length) {
						andSort1Id.append(" ) ");
					}
					System.out.println("StringBuffer andSort1Id 串接完成 :" + andSort1Id);
					
				}
				map.remove("sort1_id");
			}
		}
	
		//*****Sort1分界點*****//
		
		//*****時間分界點*****//
		String[] startTime = null;
		String[] endTime = null;
		
		String startTimeValue = null;
		String endTimeValue = null;
		StringBuffer whereTime = new StringBuffer();
		if(map.get("startTime") != null && map.get("endTime") != null) {
			System.out.println("進入時間串接");
			startTime = map.get("startTime");
			startTimeValue = startTime[0].toString();
			System.out.println(startTimeValue);
			
			endTime = map.get("endTime");
			endTimeValue = endTime[0].toString();
			System.out.println(endTimeValue);
			
			whereTime.append(" WHERE  update_time BETWEEN  ");
			whereTime.append(" ' " + startTimeValue + " ' " + " AND " + " ' " + endTimeValue +" ' " );
			map.remove("startTime");
			map.remove("endTime");
		}
	
		//*****時間分界點*****//
		
		
		String andSort1IdString = andSort1Id.toString();
		System.out.println(andSort1IdString);
		
		String whereTimeString = whereTime.toString();
		System.out.println(whereTimeString);
		
		try {
			con = getRDSConnection();
			String finalSQL = "select * from product "
					+ "JOIN p_sort1 "
					+" ON product.product_id = p_sort1.product_id "	
					// 後臺要多拉一對一的表
					+" JOIN sort2 "	
					+" ON product.sort2_id = sort2.sort2_id "	
					+ whereTimeString
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
					+ andSort1IdString
					+ "group by product.product_id "
				    + "order by product.product_id DESC LIMIT " + pageSize + " OFFSET " + pageNo;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("List<ProductVO> getAll(Map<String, String[]> map, int pageSize, int pageNo)  ●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAmount(rs.getInt("amount"));
				productVO.setUpdateTime(rs.getTimestamp("update_time"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setSort2Id(rs.getInt("sort2_id"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setTopStatus(rs.getInt("top_status"));
				// 後臺要多拉一對一的表
				productVO.setSort2Name(rs.getString("sort2_Name"));
				
				list.add(productVO); // Store the row in the List
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
	public int getAllTotalCount(Map<String, String[]> map) {
		System.out.println("後臺用的dao,算筆數");
		int total = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		for(String key : map.keySet()){
			   String[] value = map.get(key);
			   System.out.println(key+"  "+value);
			}
		
		//*****Sort1分界點*****//
		//有輸入sort1Id才串 ,sort1Id有可能是空陣列
		String[] sort1Id = null;
		StringBuffer andSort1Id = new StringBuffer();
		
		if((String[])map.get("sort1_id") != null) {
			sort1Id = (String[])map.get("sort1_id");
			
			andSort1Id.append(" AND p_sort1.sort1_id ");
			int count = 0;
			
			if(sort1Id!=null) {
				for (int i = 0; i < sort1Id.length; i++) {
					System.out.println("分類串接");
					System.out.println("從listAllproduct.jsp獲得查詢sort1["+i+"],sort1id : " + sort1Id[i]);
					count++;
					if (count == 1) {
						andSort1Id.append(" in ");
						andSort1Id.append(" ( ");
						andSort1Id.append(Integer.valueOf(sort1Id[i]));
					}
					if (count > 1) {
						andSort1Id.append(",");
						andSort1Id.append(Integer.valueOf(sort1Id[i]));
					}
					if (count == sort1Id.length) {
						andSort1Id.append(" ) ");
					}
					System.out.println("StringBuffer andSort1Id 串接完成 :" + andSort1Id);
					
				}
				map.remove("sort1_id");
			}
		}
	
		//*****Sort1分界點*****//
		
		//*****時間分界點*****//
		String[] startTime = null;
		String[] endTime = null;
		
		String startTimeValue = null;
		String endTimeValue = null;
		StringBuffer whereTime = new StringBuffer();
		if(map.get("startTime") != null && map.get("endTime") != null) {
			System.out.println("進入時間串接");
			startTime = map.get("startTime");
			startTimeValue = startTime[0].toString();
			System.out.println(startTimeValue);
			
			endTime = map.get("endTime");
			endTimeValue = endTime[0].toString();
			System.out.println(endTimeValue);
			
			whereTime.append(" AND  update_time BETWEEN  ");
			whereTime.append(" ' " + startTimeValue + " ' " + " AND " + " ' " + endTimeValue +" ' " );
			map.remove("startTime");
			map.remove("endTime");
		}
	
		//*****時間分界點*****//
		
		
		String andSort1IdString = andSort1Id.toString();
		System.out.println(andSort1IdString);
		
		String whereTimeString = whereTime.toString();
		System.out.println(whereTimeString);
		
	
		try {
			con = getRDSConnection();
			String finalSQL = "select COUNT(DISTINCT(product.product_id)) as total from product "
					+ "JOIN p_sort1 "
					+" ON product.product_id = p_sort1.product_id "	
					+ whereTimeString  
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
					+ andSort1IdString ;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("int getAllTotalCount(Map<String, String[]> map)●●finalSQL(by DAO) =" + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt("total");
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
		return total;
	}
	
	@Override
	public List<ProductVO> getForShopFront(Map<String, String[]> map, int pageSize, int pageNo) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getRDSConnection();
			String finalSQL = "select product.product_id, product_name, price from product "
					+ "JOIN p_sort1 "
					+" ON product.product_id = p_sort1.product_id "	
			        + "WHERE product.status NOT IN (0) "
			        + jdbcUtil_CompositeQuery_ProductForFront.get_WhereConditionProductForFront(map)
			        + "group by product.product_id "
			        + "order by product.product_id DESC LIMIT " + pageSize + " OFFSET " + pageNo;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("List<ProductVO> getForShopFront() ●●finalSQL(by DAO) = List<ProductVO> getForShopFront()" + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
//				productVO.setAmount(rs.getInt("amount"));
//				productVO.setUpdateTime(rs.getTimestamp("update_time"));
//				productVO.setGroupAmount1(rs.getInt("group_amount1"));
//				productVO.setGroupAmount2(rs.getInt("group_amount2"));
//				productVO.setGroupAmount3(rs.getInt("group_amount3"));
//				productVO.setGroupPrice1(rs.getInt("group_price1"));
//				productVO.setSort2Id(rs.getInt("sort2_id"));
//				productVO.setDescription(rs.getString("description"));
//				productVO.setStatus(rs.getInt("status"));
//				productVO.setTopStatus(rs.getInt("top_status"));
				list.add(productVO); // Store the row in the List
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
	public int getForShopFrontTotalCount(Map<String, String[]> map) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			con = getRDSConnection();
			String finalSQL = "select COUNT(DISTINCT(product.product_id)) as total from product "
					+ "JOIN p_sort1 "
					+" ON product.product_id = p_sort1.product_id "	
			        + "WHERE product.status NOT IN (0) "
			        + jdbcUtil_CompositeQuery_ProductForFront.get_WhereConditionProductForFront(map);
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("List<ProductVO> getForShopFront() ●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				total = rs.getInt("total");
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
		return total;
	}

	@Override
	public List<ProductVO> getForGroupShopFront(Map<String, String[]> map) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getRDSConnection();
			String finalSQL = "select * from product "
					+ "where status = 2 "
					+ "group by product.product_id "
					+ "order by product_id DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("List<ProductVO> getForGroupShopFront() ●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductId(rs.getInt("product_id"));
				productVO.setProductName(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAmount(rs.getInt("amount"));
				productVO.setUpdateTime(rs.getTimestamp("update_time"));
				productVO.setGroupAmount1(rs.getInt("group_amount1"));
				productVO.setGroupAmount2(rs.getInt("group_amount2"));
				productVO.setGroupAmount3(rs.getInt("group_amount3"));
				productVO.setGroupPrice1(rs.getInt("group_price1"));
				productVO.setSort2Id(rs.getInt("sort2_id"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setTopStatus(rs.getInt("top_status"));
				list.add(productVO); // Store the row in the List
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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

}

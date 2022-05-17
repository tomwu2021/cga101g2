package com.product.model;

import static connection.JDBCConnection.getRDSConnection;

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

public class ProductJDBCDAO implements ProductDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO cga_02.product(product_name,price,amount, "
			+ "group_amount1,group_amount2,group_amount3,group_price1, " + "sort2_id,description,status,top_status) "
			+ "VALUES (?, ? , ?, ? , ? , ? , ? , ? , ? , 0 , 0 ) ; ";
	private static final String GET_ALL_STMT = "SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status "
			+ "FROM cga_02.product ORDER BY  product_id DESC LIMIT 15 ; ";
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
				pstmt.setInt(2, productVO.getAmount());
			}
			if (amount > 0) {
				pstmt.setInt(3, productVO.getPrice());
			}
			if (groupAmount1 > 0) {
				pstmt.setInt(4, productVO.getPrice());
			}
			if (groupAmount2 > 0) {
				pstmt.setInt(5, productVO.getPrice());
			}
			if (groupAmount3 > 0) {
				pstmt.setInt(6, productVO.getPrice());
			}
			if (groupPrice1 > 0) {
				pstmt.setInt(7, productVO.getPrice());
			}
			pstmt.setInt(8, productVO.getSort2Id());
			if (description != null && !description.isEmpty()) {
				pstmt.setString(9, productVO.getProductName());
			}
			pstmt.setInt(10, productVO.getProductId());

			System.out.println("ProductVO update送出的字串"+UPDATE);
			
			pstmt.executeUpdate();

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productVO;

	}

	@Override
	public boolean delete(ProductVO productVO) {
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE)) {

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
	public boolean deleteByTopStatus(Integer productId, Integer topStatus) {
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(DELETEBYTOP_STATUS)) {

			pstmt.setInt(1, topStatus);
			pstmt.setInt(2, productId);

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
		ProductImgVO pImgVO = new ProductImgVO();
		
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_PIMGs_ByPId_STMT)) {
			pstmt.setInt(1, pdID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
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
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductVO productVO = new ProductVO();
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
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getRDSConnection();
			String finalSQL = "select * from product "
					+ "JOIN p_sort1 "
					+" ON product.product_id = p_sort1.product_id "	
					+ jdbcUtil_CompositeQuery_Product.get_WhereCondition(map)
					+ "group by product.product_id "
					+ "order by product.product_id DESC";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("List<ProductVO> getAll(Map<String, String[]> map) ●●finalSQL(by DAO) = " + finalSQL);
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

//	ShopPublic公開頁面 只接受參數status=1 or Status =2
	@Override
	public List<ProductVO> getForShopFront(Map<String, String[]> map, int pageSize, int pageNo) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getRDSConnection();
			String finalSQL = "select * from product "
					+ "where status =1 "
					+ "or status = 2 "
					+ "group by product.product_id "
					+ "order by product_id ";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println(" List<ProductVO> getForShopFront() ●●finalSQL(by DAO) = " + finalSQL);
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

	//	ShopPublic公開頁面 只接受參數status=2
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
					+ "order by product_id ";
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

	@Override
	public int getForShopFrontTotalCount(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAllTotalCount(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ProductVO checkProdcutName(String prodcutName) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	
//	@Override
	// public ProductVO getProductVOByID(int id) {

	// ProductVO productVO = new ProductVO();
	// List<ProductImgVO> productImgVOs = new ArrayList<ProductImgVO>();
	// try (Connection connection = getRDSConnection();
	// PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT_ANDIMG)) {
	//
	// pstmt.setInt(1, id);
	// ResultSet rs = pstmt.executeQuery();
	// while (rs.next()) {
	// //儲存各自的數據
	// //儲存商品數據
	// productVO.setProductId(rs.getInt("product_id"));
	// productVO.setProductName(rs.getString("product_name"));
	// productVO.setPrice(rs.getInt("price"));
	// productVO.setAmount(rs.getInt("amount"));
	// productVO.setUpdateTime(rs.getTimestamp("update_time"));
	// productVO.setGroupAmount1(rs.getInt("group_amount1"));
	// productVO.setGroupAmount2(rs.getInt("group_amount2"));
	// productVO.setGroupAmount3(rs.getInt("group_amount3"));
	// productVO.setGroupPrice1(rs.getInt("group_price1"));
	// productVO.setSort2Id(rs.getInt("sort2_id"));
	// productVO.setDescription(rs.getString("description"));
	// productVO.setStatus(rs.getInt("status"));
	// productVO.setTopStatus(rs.getInt("top_status"));
	// //儲存照片數據
	// ProductImgVO pImgVO = new ProductImgVO();
	// pImgVO.setProductImgId(rs.getInt("product_img_id"));
	// pImgVO.setProductId(rs.getInt("product_id"));
	// pImgVO.setProductImgUrl(rs.getString("product_img_url"));
	// pImgVO.setFileKey(rs.getString("file_key"));
	// pImgVO.setFileName(rs.getString("file_name"));
	// pImgVO.setSize(rs.getString("size"));
	// pImgVO.setPreviewUrl(rs.getString("preview_url"));
	// //把一個pImgVO放入集合中
	// productImgVOs.add(pImgVO);
	// }
	// //3.建立商品跟照片"集合"實體的關係
	// productVO.setProductId(productImgVOs);
	// return productVO;
	// } catch (SQLException se) {
	// throw new RuntimeException("A database error occured. " + se.getMessage());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
}

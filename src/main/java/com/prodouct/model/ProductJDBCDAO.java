package com.prodouct.model;

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


public class ProductJDBCDAO implements ProductDAO_interface{

	private static final String INSERT_STMT = 
			"INSERT INTO product(product_name,price,amount, "
			+ "group_amount1,group_amount2,group_amount3,group_price1, "
			+ "sort2_id,description,status,top_status) "
			+ "VALUES (?, ? , ?, ? , ? , ? , ? , ? , ? , 0 , 0 );";
		private static final String GET_ALL_STMT = 
			"SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status "
			+ "FROM cga_02.product  ; ";
		private static final String GET_ONE_STMT = 
			"SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status "
			+ "FROM cga_02.product "
			+ "WHERE product_id = ? ; ";
		private static final String DELETE = 
			"UPDATE  cga_02.product "
			+ "SET status = ? "
			+ "WHERE product_id = ? ;";
		
		private static final String DELETEBYTOP_STATUS = 
				"UPDATE  cga_02.product "
				+ "SET top_status = ? "
				+ "WHERE product_id = ? ;";
		
		private static final String UPDATE = 
			"UPDATE  cga_02.product "
			+ "SET product_name = ? , "
			+ "price = ? ,"
			+ "amount = ? ,"
			+ "group_amount1 = ? ,"
			+ "group_amount2 = ? ,"
			+ "group_amount3 = ? ,"
			+ "group_price1 = ? ,"
			+ "sort2_id = ? ,"
			+ "description = ? "
			+ "WHERE product_id = ? ; ";
		
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setInt(2, productVO.getPrice());
			pstmt.setInt(3, productVO.getAmount());
			pstmt.setInt(4, productVO.getGroup_amount1());
			pstmt.setInt(5, productVO.getGroup_amount2());
			pstmt.setInt(6, productVO.getGroup_amount3());
			pstmt.setInt(7, productVO.getGroup_price1());
			pstmt.setInt(8, productVO.getSort2_id());
			pstmt.setString(9, productVO.getDescription());

			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) updated!");


			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
					System.out.println("insert successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		

	@Override
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProduct_name());
			pstmt.setInt(2, productVO.getPrice());
			pstmt.setInt(3, productVO.getAmount());
			pstmt.setInt(4, productVO.getGroup_amount1());
			pstmt.setInt(5, productVO.getGroup_amount2());
			pstmt.setInt(6, productVO.getGroup_amount3());
			pstmt.setInt(7, productVO.getGroup_price1());
			pstmt.setInt(8, productVO.getSort2_id());
			pstmt.setString(9, productVO.getDescription() );
			pstmt.setInt(10, productVO.getProduct_id());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
					System.out.println("update successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		
	}

	@Override
	public void delete(Integer status ,Integer product_id) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(DELETE);
	
			pstmt.setInt(1, status);
			pstmt.setInt(2, product_id);
	
			pstmt.executeUpdate();
	
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
					System.out.println("delete successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		
	}


	@Override
	public void updatetBytop_status(Integer top_status ,Integer product_id) {
		//! TODO Auto-generated method stub 防呆1跟2的部分
		
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
	
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(DELETEBYTOP_STATUS);
	
			pstmt.setInt(1, top_status);
			pstmt.setInt(2, product_id);
	
			pstmt.executeUpdate();
	
			// Handle any driver errors
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			}
			if (con != null) {
				try {
					con.close();
					System.out.println("updatetBytop_status successfully...");
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		
	}


	@Override
	public ProductVO findByPrimaryKey(Integer product_id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, product_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getInt("product_id"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAmount(rs.getInt("amount"));
				productVO.setUpdate_time(rs.getTimestamp("update_time"));
//				System.out.println(rs.getTimestamp("update_time").getClass());
				productVO.setGroup_amount1(rs.getInt("group_amount1"));
				productVO.setGroup_amount2(rs.getInt("group_amount2"));
				productVO.setGroup_amount3(rs.getInt("group_amount3"));
				productVO.setGroup_price1(rs.getInt("group_price1"));
				productVO.setSort2_id(rs.getInt("sort2_id"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setTop_status(rs.getInt("top_status"));
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
				} catch (Exception e) {
					e.printStackTrace(System.err);
					System.out.println("findByPrimaryKey successfully...");
				}
			}
		}
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = DriverManager.getConnection(theURL, username, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				productVO = new ProductVO();
				productVO.setProduct_id(rs.getInt("product_id"));
				productVO.setProduct_name(rs.getString("product_name"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setAmount(rs.getInt("amount"));
				productVO.setUpdate_time(rs.getTimestamp("update_time"));
//				System.out.println(rs.getTimestamp("update_time").getClass());
				productVO.setGroup_amount1(rs.getInt("group_amount1"));
				productVO.setGroup_amount2(rs.getInt("group_amount2"));
				productVO.setGroup_amount3(rs.getInt("group_amount3"));
				productVO.setGroup_price1(rs.getInt("group_price1"));
				productVO.setSort2_id(rs.getInt("sort2_id"));
				productVO.setDescription(rs.getString("description"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setTop_status(rs.getInt("top_status"));
				list.add(productVO);
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
		
		ProductJDBCDAO dao = new ProductJDBCDAO();
		
		// 新增
//		ProductVO ProductVO1 = new ProductVO();
//		ProductVO1.setProduct_name("罐頭");
//		ProductVO1.setPrice(600);
//		ProductVO1.setAmount(30);
//		ProductVO1.setGroup_amount1(50);
//		ProductVO1.setGroup_amount2(60);
//		ProductVO1.setGroup_amount3(70);
//		ProductVO1.setGroup_price1(580);
//		ProductVO1.setSort2_id(3);
//		ProductVO1.setDescription("好吃");
//		dao.insert(ProductVO1);
		
		//更改一般商品
//		ProductVO ProductVO2 = new ProductVO();
//		ProductVO2.setProduct_name("罐頭2212DDDD222頭");
//		ProductVO2.setPrice(600);
//		ProductVO2.setAmount(203);
//		ProductVO2.setGroup_amount1(40);
//		ProductVO2.setGroup_amount2(50);
//		ProductVO2.setGroup_amount3(70);
//		ProductVO2.setGroup_price1(70);
//		ProductVO2.setSort2_id(7);
//		ProductVO2.setDescription("好吃");
//		ProductVO2.setProduct_id(2);
//		dao.update(ProductVO2);
		
		//更改一般商品與團購的上下架狀態
//	    0:下架  1:一般商品上架 2:一般商品+團購上架
//		dao.delete(2,1);
		
		//更改一般商品的推薦狀態 
//		dao.updatetBytop_status(0,1);
		
		//查詢一筆商品
//		ProductVO ProductVO3 = dao.findByPrimaryKey(3);
//		System.out.print(ProductVO3.getProduct_id() + ",");
//		System.out.print(ProductVO3.getProduct_name() + ",");
//		System.out.print(ProductVO3.getPrice() + ",");
//		System.out.print(ProductVO3.getAmount() + ",");
//		System.out.print(ProductVO3.getUpdate_time() + ",");
//		System.out.print(ProductVO3.getGroup_amount1() + ",");
//		System.out.print(ProductVO3.getGroup_amount2() + ",");
//		System.out.print(ProductVO3.getGroup_amount3() + ",");
//		System.out.print(ProductVO3.getGroup_price1() + ",");
//		System.out.print(ProductVO3.getSort2_id() + ",");
//		System.out.print(ProductVO3.getDescription() + ",");
//		System.out.print(ProductVO3.getStatus() + ",");
//		System.out.println(ProductVO3.getTop_status());
//		System.out.println("---------------------");
		
		List<ProductVO> list = dao.getAll();
		for (ProductVO aProductVO : list) {
			System.out.print(aProductVO.getProduct_id() + ",");
			System.out.print(aProductVO.getProduct_name() + ",");
			System.out.print(aProductVO.getPrice() + ",");
			System.out.print(aProductVO.getAmount() + ",");
			System.out.print(aProductVO.getUpdate_time() + ",");
			System.out.print(aProductVO.getGroup_amount1() + ",");
			System.out.print(aProductVO.getGroup_amount2() + ",");
			System.out.print(aProductVO.getGroup_amount3() + ",");
			System.out.print(aProductVO.getGroup_price1() + ",");
			System.out.print(aProductVO.getSort2_id() + ",");
			System.out.print(aProductVO.getDescription() + ",");
			System.out.print(aProductVO.getStatus() + ",");
			System.out.println(aProductVO.getTop_status());
			System.out.println("---------------------");
		}
		
		
		
	}


}

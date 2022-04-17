package com.prodouct.model;

import static connection.JDBCConnection.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJDBCDAO implements ProductDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO cga_02.product(product_name,price,amount, "
			+ "group_amount1,group_amount2,group_amount3,group_price1, " + "sort2_id,description,status,top_status) "
			+ "VALUES (?, ? , ?, ? , ? , ? , ? , ? , ? , 0 , 0 );";
	private static final String GET_ALL_STMT = "SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status " + "FROM cga_02.product  ; ";
	private static final String GET_ONE_STMT = "SELECT product_id, product_name, price ,amount , update_time , "
			+ "group_amount1 ,group_amount2 ,group_amount3 ,group_price1 , "
			+ "sort2_id , description, status ,top_status " + "FROM cga_02.product " + "WHERE product_id = ? ; ";
	private static final String DELETE = "UPDATE  cga_02.product " + "SET status = ? " + "WHERE product_id = ? ;";

	private static final String DELETEBYTOP_STATUS = "UPDATE  cga_02.product " + "SET top_status = ? "
			+ "WHERE product_id = ? ;";



	@Override
	public ProductVO insert(ProductVO productVO) {
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {
	
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
			System.out.println(rowCount + "row(s) insert!");
			return productVO;
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
				pstmt.setInt(2, productVO.getPrice());
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
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(DELETE)) {
	
			pstmt.setInt(1, productVO.getStatus());
			pstmt.setInt(2, productVO.getProductId());
	
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
	public int deleteByTopStatus(ProductVO productVO) {
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(DELETEBYTOP_STATUS)) {
	
			pstmt.setInt(1, productVO.getTopStatus());
			pstmt.setInt(2, productVO.getProductId());
			
			int rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public ProductVO getOneById(Integer id) {
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT)) {
	
			pstmt.setInt(1, id);
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
				return productVO;
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductVO> getAll() {
		try (Connection con = getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
			List<ProductVO> list = new ArrayList<ProductVO>();
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
			return list;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

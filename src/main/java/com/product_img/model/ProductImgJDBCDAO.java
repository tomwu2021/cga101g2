package com.product_img.model;

import static connection.JDBCConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.emp.model.EmpVO;
import com.product.model.ProductVO;

public class ProductImgJDBCDAO implements ProductImgDAO_interface {

	public ProductImgVO insert(ProductImgVO pImgVO) {
		String INSERT_STMT = "INSERT INTO product_img(product_id,product_img_url, file_key, file_name, size, preview_url) "
				+ "VALUES(?,?,?,?,?,?,?)";
		try (Connection con = getRDSConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_STMT)) {

			stmt.setInt(1, pImgVO.getProductId());
			stmt.setString(2, pImgVO.getProductImgUrl());
			stmt.setString(3, pImgVO.getFileKey());
			stmt.setString(4, pImgVO.getFileName());
			stmt.setString(5, pImgVO.getSize());
			stmt.setString(6, pImgVO.getPreviewUrl());
			stmt.execute();
//			ResultSet rs = stmt.getGeneratedKeys();//獲得自動新增的主鍵
			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
			return pImgVO;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean delete(ProductImgVO pImgVO) {
		String DELETE_STMT = "DELETE FROM product_img WHERE product_img_id= ?";
		try (Connection con = getRDSConnection(); PreparedStatement stmt = con.prepareStatement(DELETE_STMT)) {

			stmt.setInt(1, pImgVO.getProductImgId());
			
			int rowCount = stmt.executeUpdate();
			System.out.println(rowCount + "row(s) delete!");
			return true;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ProductImgVO update(ProductImgVO t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductImgVO getOneById(Integer id) {
		String GET_ONE_STMT =
				"SELECT product_img_id,product_id,product_img_url,file_key,file_name,size,preview_url"
				+ "FROM cga_02.product_img "
				+ "WHERE product_img_id = ?; ";
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT)) {
	
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductImgVO pImgVO = new ProductImgVO();
				ProductImgVO pvo= new ProductImgVO();
				pvo.setProductImgId(rs.getInt("product_img_id"));
				pvo.setProductId(rs.getInt("product_id"));
				pvo.setProductImgUrl(rs.getString("product_img_url"));
				pvo.setFileKey(rs.getString("file_key"));
				pvo.setFileName(rs.getString("file_name"));
				pvo.setSize(rs.getString("size"));
				pvo.setPreviewUrl(rs.getString("preview_url"));
				return pImgVO;
			}
	
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ProductImgVO> getAll() {
	final String GET_ALL_STMT =
			"SELECT product_img_id,product_id,product_img_url,file_key,file_name,size,preview_url "
			+ "FROM product_img "
			+ "order by product_img_id" ;
			try (Connection con = getRDSConnection();
					PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
				ResultSet rs = pstmt.executeQuery();
				List<ProductImgVO> list = new ArrayList<ProductImgVO>();
				
				while (rs.next()) {
					ProductImgVO pvo= new ProductImgVO();
					pvo.setProductImgId(rs.getInt("product_img_id"));
					pvo.setProductId(rs.getInt("product_id"));
					pvo.setProductImgUrl(rs.getString("product_img_url"));
					pvo.setFileKey(rs.getString("file_key"));
					pvo.setFileName(rs.getString("file_name"));
					pvo.setSize(rs.getString("size"));
					pvo.setPreviewUrl(rs.getString("preview_url"));
					
					list.add(pvo);
				}
				return list;
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;		
	
	}

	@Override
	public List<ProductImgVO> getImgsByProductId(Integer productId) {
		
		final String GET_Imgs_ByPID_STMT = "SELECT product_img_id,product_id,product_img_url,"
											+ "file_key,file_name,size,preview_url FROM product_img "
											+ "where product_id = ? "
											+ "order by product_img_id";
		List<ProductImgVO> set = new ArrayList<ProductImgVO>();
		try (Connection con = getRDSConnection(); 
				PreparedStatement stmt = con.prepareStatement(GET_Imgs_ByPID_STMT)) {

			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
	
			while (rs.next()) {
				ProductImgVO pvo= new ProductImgVO();
				pvo.setProductImgId(rs.getInt("product_img_id"));
				pvo.setProductId(rs.getInt("product_id"));
				pvo.setProductImgUrl(rs.getString("product_img_url"));
				pvo.setFileKey(rs.getString("file_key"));
				pvo.setFileName(rs.getString("file_name"));
				pvo.setSize(rs.getString("size"));
				pvo.setPreviewUrl(rs.getString("preview_url"));
				set.add(pvo); // Store the row in the vector
			}
//			int rowCount = stmt.executeUpdate();
//			System.out.println(rowCount + "row(s) delete!");
			System.out.println("Size = " + set.size());
			return set;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
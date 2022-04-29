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
import com.picture.model.PictureVO;
import com.product.model.ProductVO;

public class ProductImgJDBCDAO implements ProductImgDAO_interface {

	public ProductImgVO insert(ProductImgVO pImgVO) {
		String INSERT_STMT = "INSERT INTO product_img(product_img_id,product_id) "
				 			+ "VALUES(?,?)";
		try (Connection con = getRDSConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_STMT)) {

			stmt.setInt(1, pImgVO.getProductId());
			stmt.setInt(2, pImgVO.getProductImgId());
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
				"SELECT product_img_id,product_id "
				+ "FROM cga_02.product_img  "
				+ "WHERE product_img_id = ? ";
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT)) {
	
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductImgVO pImgVO = new ProductImgVO();
				pImgVO.setProductImgId(rs.getInt("product_img_id"));
				pImgVO.setProductId(rs.getInt("product_id"));
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
			"SELECT product_img_id,product_id "
			+ "FROM product_img  "
			+ "order by product_img_id " ;
			try (Connection con = getRDSConnection();
					PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
				ResultSet rs = pstmt.executeQuery();
				List<ProductImgVO> list = new ArrayList<ProductImgVO>();
				
				while (rs.next()) {
					ProductImgVO pvo= new ProductImgVO();
					pvo.setProductImgId(rs.getInt("product_img_id"));
					pvo.setProductId(rs.getInt("product_id"));
					
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

	
	//用產品ID找到產品照片的"集合"
	@Override
	public List<PictureVO> getPicVOsByProductId(Integer productId) {

		final String GET_Imgs_ByPID_STMT = "SELECT * FROM product_img JOIN picture "
										+ "ON product_img_id = picture_id "
										+ "WHERE product_id = ?; ";
		List<PictureVO> list = new ArrayList<PictureVO>();
		try (Connection con = getRDSConnection(); 
				PreparedStatement stmt = con.prepareStatement(GET_Imgs_ByPID_STMT)) {

			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
	
			while (rs.next()) {
				PictureVO pictureVO= new PictureVO();
				pictureVO.setPictureId(rs.getInt("picture_id"));
				pictureVO.setUrl(rs.getString("url"));
				pictureVO.setCreateTime(rs.getTimestamp("upload_time"));
				pictureVO.setFileKey(rs.getString("file_key"));
				pictureVO.setFileName(rs.getString("file_name"));
				pictureVO.setSize(rs.getLong("size"));
				pictureVO.setPreviewUrl(rs.getString("preview_url"));
				pictureVO.setPreviewKey(rs.getString("preview_key"));
				System.out.println("picture_id = " + pictureVO.getPictureId());
				list.add(pictureVO); // Store the row in the vector
			}
			System.out.println("Size = " + list.size());
			
			System.out.println("List<PictureVO> getPicVOsByProductId(Integer productId) 執行成功");
			return list;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
//			throw new RuntimeException("A database error occured. " + e.getMessage());
		}
		return null;
	}

}
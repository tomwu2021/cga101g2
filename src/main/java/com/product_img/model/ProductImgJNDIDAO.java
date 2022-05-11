package com.product_img.model;

import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.picture.model.PictureVO;

public class ProductImgJNDIDAO implements ProductImgDAO_interface {


	public ProductImgVO insert(ProductImgVO pImgVO) {
		// 設定自動新增的主鍵名稱
		String columns[] = { "product_img_id" };
		String INSERT_STMT = "INSERT INTO product_img(product_img_id,product_id) " + "VALUES(?,?)";
		try (Connection con = getRDSConnection(); PreparedStatement stmt = con.prepareStatement(INSERT_STMT, columns)) {
			stmt.setInt(1, pImgVO.getProductImgId());
			stmt.setInt(2, pImgVO.getProductId());
//					stmt.execute(); !!就是你重複提交資料 懷懷!!!!

			int rowCount = stmt.executeUpdate();
			System.out.println("ProductImgVO " + rowCount + "row(s) insert!");

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
		String GET_ONE_STMT = "SELECT product_img_id,product_id,img " + "FROM cga_02.product_img  "
				+ "WHERE product_img_id = ? ";
		try (Connection connection = getRDSConnection();
				PreparedStatement pstmt = connection.prepareStatement(GET_ONE_STMT)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductImgVO pImgVO = new ProductImgVO();
				pImgVO.setProductImgId(rs.getInt("product_img_id"));
				pImgVO.setProductId(rs.getInt("product_id"));
				pImgVO.setImage(rs.getBytes("img"));
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
		final String GET_ALL_STMT = "SELECT product_img_id,product_id,img  " + "FROM product_img  "
				+ "order by product_img_id ";
		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT)) {
			ResultSet rs = pstmt.executeQuery();
			List<ProductImgVO> list = new ArrayList<ProductImgVO>();

			while (rs.next()) {
				ProductImgVO pImgVO = new ProductImgVO();
				pImgVO.setProductImgId(rs.getInt("product_img_id"));
				pImgVO.setProductId(rs.getInt("product_id"));
				pImgVO.setImage(rs.getBytes("img"));

				list.add(pImgVO);
			}
			return list;
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	// 用產品ID找到產品照片的"集合"
	@Override
	public List<PictureVO> getPicVOsByProductId(Integer productId) {

		final String GET_PicVOs_ByPID_STMT = "SELECT * FROM product_img JOIN picture "
				+ "ON product_img_id = picture_id " + "WHERE product_id = ?; ";
		List<PictureVO> list = new ArrayList<PictureVO>();
		try (Connection con = getRDSConnection();
				PreparedStatement stmt = con.prepareStatement(GET_PicVOs_ByPID_STMT)) {

			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				PictureVO pictureVO = new PictureVO();
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
		}
		return null;
	}

	@Override
	public List<ProductImgVO> getProductImgVOsByProductId(Integer productId) {

		final String GET_PImgVOs_ByPID_STMT = 
				"SELECT product_img_id,product_id,img "
				+ "FROM cga_02.product_img  "
				+ "WHERE product_id = ? "
				+ "ORDER BY product_img_id ; " ;
		List<ProductImgVO> list = new ArrayList<ProductImgVO>();
		try (Connection con = getRDSConnection(); 
				PreparedStatement stmt = con.prepareStatement(GET_PImgVOs_ByPID_STMT)) {

			stmt.setInt(1, productId);
			ResultSet rs = stmt.executeQuery();
	
			while (rs.next()) {
				ProductImgVO PdImgVO= new ProductImgVO();
				PdImgVO.setProductImgId(rs.getInt("product_img_id"));
				PdImgVO.setProductId(rs.getInt("product_id"));
				PdImgVO.setImage(rs.getBytes("img"));
				list.add(PdImgVO); // Store the row in the vector
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
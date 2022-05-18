package com.wishlist.model;

import static connection.JNDIConnection.getRDSConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JNDIConnection;

public class WishlistJNDIDAO implements WishlistDAO_interface {

	// 情境一 insert：新增一筆資料
	@Override
	public boolean insertWishlistVO(WishlistVO wishlistVO) {
		int rowCount = 0;
		final String INSERT_STMT = "INSERT INTO wishlist(member_id, product_id) values(?,?);";
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

			pstmt.setInt(1, wishlistVO.getMemberId());
			pstmt.setInt(2, wishlistVO.getProductId());

			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) insert!");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (rowCount == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public WishlistVO insert(WishlistVO wishlistVO) {
		return null;
	}

	// 情境二 delete：刪除一筆資料
	@Override
	public boolean delete(WishlistVO wishlistVO) {
		int rowCount = 0;
		final String DELETE = "DELETE FROM wishlist WHERE member_id = ? and product_id = ?";

		try (Connection con = getRDSConnection(); PreparedStatement pstmt = con.prepareStatement(DELETE)) {

			pstmt.setInt(1, wishlistVO.getMemberId());
			pstmt.setInt(2, wishlistVO.getProductId());

			rowCount = pstmt.executeUpdate();
			System.out.println(rowCount + "row(s) delete!");
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		if (rowCount == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public WishlistVO update(WishlistVO wishlistVO) {
		return null;
	}

	@Override
	public WishlistVO getOneById(Integer id) {
		return null;
	}

	@Override
	public List<WishlistVO> getAll() {
		return null;
	}

	@Override
	public List<WishlistVO> getAllByMemberId(Integer memberId) {
		List<WishlistVO> list = new ArrayList<>();
		final String GETALL = "SELECT member_id, product.product_id , product_name , price , create_time "
				+ "FROM wishlist " 
				+ "JOIN product " 
				+ "ON wishlist.product_id = product.product_id "
				+ "WHERE member_id = ? "
				+ "AND product.STATUS NOT IN(0) " 
				+ "ORDER BY create_time DESC ;";

		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {

			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				WishlistVO newWishlist = new WishlistVO();
				newWishlist.setMemberId(rs.getInt("member_id"));
				newWishlist.setProductId(rs.getInt("product_id"));
				newWishlist.setProductName(rs.getString("product_name"));
				newWishlist.setPrice(rs.getInt("price"));
				newWishlist.setCreate_time(rs.getTimestamp("create_time"));
				list.add(newWishlist);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public WishlistVO getOneWishlistVOForCheck(Integer memberId, Integer productId) {
		WishlistVO wishlistVO = new WishlistVO();
		final String SELECT_STMT = "SELECT  member_id, product_id "
								+ "FROM  cga_02.wishlist "
								+ "WHERE  member_id = ? "
								+ "AND  product_id = ? " ;
		System.out.println(SELECT_STMT);
		try (Connection con = getRDSConnection(); 
				PreparedStatement pstmt = con.prepareStatement(SELECT_STMT)) {

			pstmt.setInt(1, memberId );
			pstmt.setInt(2, productId );

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				wishlistVO.setMemberId(rs.getInt("member_id"));
				wishlistVO.setProductId(rs.getInt("product_id"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
			return wishlistVO;
	}

}

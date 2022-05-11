package com.wishlist.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.JNDIConnection;


public class WishlistJNDIDAO implements WishlistDAO_interface{

	Connection con;
	
	// 情境一 insert：新增一筆資料
	@Override
	public WishlistVO insert(WishlistVO wishlistVO) {
		con = JNDIConnection.getRDSConnection();
		WishlistVO wishlistVO2 = insert(wishlistVO, con);
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishlistVO2;
	}
	public WishlistVO insert(WishlistVO wishlistVO, Connection con) {
		final String INSERT_STMT = "INSERT INTO wishlist(member_id, product_id) values(?,?);";
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT, Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, wishlistVO.getMemberId());
				pstmt.setInt(2, wishlistVO.getProductId());
				pstmt.executeUpdate();

				ResultSet rs = pstmt.getGeneratedKeys();
				return wishlistVO;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 情境二 delete：刪除一筆資料
	@Override
	public boolean delete(WishlistVO wishlistVO) {
		con = JNDIConnection.getRDSConnection();
		boolean boo = delete(wishlistVO, con);
		
		try {
			con.close();
			return boo;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(WishlistVO wishlistVO,Connection con) {
		final String DELETE = "DELETE FROM wishlist WHERE member_id = ? and product_id = ?";
		
		if (con != null) {
			try {
				PreparedStatement pstmt = con.prepareStatement(DELETE);
				pstmt.setInt(1, wishlistVO.getMemberId());
				pstmt.setInt(2, wishlistVO.getProductId());
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}	
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
		final String GETALL = "SELECT * FROM wishlist where member_id = ? ORDER BY create_time ;";

		try (Connection con = JNDIConnection.getRDSConnection();
				PreparedStatement pstmt = con.prepareStatement(GETALL)) {
			
			pstmt.setInt(1, memberId);
			ResultSet rs = pstmt.executeQuery();

			List<WishlistVO> list = new ArrayList<>();
			while (rs.next()) {
				WishlistVO newWishlist = new WishlistVO();
				newWishlist.setMemberId(rs.getInt("member_id"));
				newWishlist.setProductId(rs.getInt("product_id"));
				newWishlist.setCreate_time(rs.getTimestamp("create_time"));
				list.add(newWishlist);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

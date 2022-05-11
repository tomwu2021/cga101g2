package com.wishlist.model;

import java.util.List;

public class TestWishlistDAO {

	public static void main(String[] args) {
		WishlistJDBCDAO dao = new WishlistJDBCDAO();
		// 情境一 insert：新增一筆資料
//		WishlistVO wishlistVO1 = new WishlistVO();
//		wishlistVO1.setMemberId(3);
//		wishlistVO1.setProductId(5);
//		System.out.println(dao.insert(wishlistVO1));

		// 情境二 delete：刪除一筆資料
//		WishlistVO wishlistVO2 = new WishlistVO();
//		wishlistVO2.setMemberId(3);
//		wishlistVO2.setProductId(4);
//		System.out.println(dao.delete(wishlistVO2));

		// 情境三 select：查詢某會員所有清單資料
		for (WishlistVO wishlistVO3 : dao.getAllByMemberId(3)) {
			System.out.println(wishlistVO3);
		}
	}

}

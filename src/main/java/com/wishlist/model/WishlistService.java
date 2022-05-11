package com.wishlist.model;

import java.util.List;

public class WishlistService {

//	private:僅類別內有效
	private WishlistJNDIDAO dao;

//	改寫預設建構子
	public WishlistService() {
		dao = new WishlistJNDIDAO(); 
	}
	
	public List<WishlistVO> getAllByMemberId (Integer memberId) {
		return dao.getAllByMemberId(memberId);
	}
	
	public boolean delete(WishlistVO wishlistVO) {
		return dao.delete(wishlistVO);
	}
	
	public WishlistVO insert(WishlistVO wishlistVO) {
		return dao.insert(wishlistVO);
	}
}

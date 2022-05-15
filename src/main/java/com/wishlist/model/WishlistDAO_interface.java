package com.wishlist.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;

public interface WishlistDAO_interface extends JDBCDAO_Interface<WishlistVO>{
	List<WishlistVO> getAllByMemberId(Integer memberId);
	
	boolean insertWishlistVO(WishlistVO wishlistVO);
	
	//給單個商品頁面,輸入memberId 跟 productId 回傳單個wishlistVO
	WishlistVO  getOneWishlistVOForCheck(Integer memberId,Integer productId);
}

package com.wishlist.model;

import java.util.List;
import com.common.model.JDBCDAO_Interface;

public interface WishlistDAO_interface extends JDBCDAO_Interface<WishlistVO>{
	List<WishlistVO> getAllByMemberId(Integer memberId);
}

package com.wishlist.model;

import java.util.List;
import com.wishlist.model.WishlistVO;

public interface WishlistDAO {
	// Ω∆¶X•D¡‰
	public void insert(WishlistVO wishlistVO);

	public void update(WishlistVO wishlistVO);

	public void delete(Integer member_id, Integer product_id);

	public WishlistVO findByPrimaryKey(Integer member_id, Integer product_id);

	public List<WishlistVO> getAll();

}

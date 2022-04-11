package com.wishlist.model;

import java.sql.Timestamp;

public class WishlistVO implements java.io.Serializable {
	// Ω∆¶X•D¡‰ member_id°Bproduct_id
	private Integer member_id;
	private Integer product_id;
	private Timestamp create_time;

	public WishlistVO() {
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

}

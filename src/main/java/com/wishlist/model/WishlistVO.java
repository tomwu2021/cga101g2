package com.wishlist.model;

import java.sql.Timestamp;

public class WishlistVO implements java.io.Serializable {

	private Integer memberId;
	private Integer productId;
	private Timestamp createTime;

	public WishlistVO() {
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Timestamp getCreate_time() {
		return createTime;
	}

	public void setCreate_time(Timestamp create_time) {
		this.createTime = create_time;
	}

}

package com.wishlist.model;

import java.sql.Timestamp;
import java.util.List;

import com.picture.model.PictureVO;

public class WishlistVO implements java.io.Serializable {

	private Integer memberId;
	private Integer productId;
	private Timestamp createTime;
	private String productName;
	private Integer price;
	private List<PictureVO> pictureVOList;
	
	
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<PictureVO> getPictureVOList() {
		return pictureVOList;
	}

	public void setPictureVOList(List<PictureVO> pictureVOList) {
		this.pictureVOList = pictureVOList;
	}

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

	@Override
	public String toString() {
		return "WishlistVO [memberId=" + memberId + ", productId=" + productId + ", createTime=" + createTime + "]";
	}

}
